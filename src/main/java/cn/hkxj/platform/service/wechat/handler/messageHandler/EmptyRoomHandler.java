package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.Building;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.RoomTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.EmptyRoomService;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
@Component
@Slf4j
public class EmptyRoomHandler implements WxMpMessageHandler {
	private static final String PATTERN = "格式不正确:\n\n具体教室 \n：空教室 教室 主楼E0405\n（主楼教室前要加主楼俩字 科厦教室需要加上科字如：科S308）\n\n查询教学楼的某一层：\n例如查询科厦四楼空教室\n空教室 科厦 4";
	private static Splitter SPLITTER = Splitter.on(" ").trimResults().omitEmptyStrings();
	private static final int CONTENT_SIZE = 3;
	private static final String SINGLE_ROOM = "教室";
	@Resource(name = "emptyRoomService")
	private EmptyRoomService roomService;
	@Resource
	private CourseService courseService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager){
		String reply = parseContent(wxMessage.getContent());
		log.info("check empty room success openid:{}", wxMessage.getFromUser());

		return new TextBuilder().build(reply, wxMessage, wxMpService);
	}

	public String parseContent(String content){
		String[] strings = StreamSupport.stream(SPLITTER.split(content).spliterator(), false).toArray(String[]::new);
		if ((strings.length != CONTENT_SIZE)){
			return PATTERN;
		}
		String searchType = strings[1];
		if (searchType.equals(SINGLE_ROOM)){
			return searchSingleRoom(strings[2]);
		}
		else {

			Building building = Building.getBuildingByName(searchType);
			Integer floor = new Integer(strings[2]);
			if ((!isParamValid(building, floor))){
				return "你的查询超出了我们理解范围";
			}
			return getReply(roomService.getTodayRoomTimeTable(building, floor));
		}
	}

	/**
	 * 单个教室的使用情况详情
	 * @param name 教室名
	 */
	private String searchSingleRoom(String name) {
		RoomTimeTable roomTimeTable = roomService.getTodayTimeTableByRoomName(name);
		return getReply(roomTimeTable);
	}

	/**
	 * 教学楼对应楼层的教室使用概况
	 * @param building
	 * @param floor
	 * @return
	 */
	private String searchListRoom(String building, String floor){
		if(!Building.isExist(building)){
			return PATTERN;
		}
		if (!StringUtils.isNumeric(floor)){
			return "输入查询楼层对应的数字";
		}
		List<RoomTimeTable> table = roomService.getTodayRoomTimeTable(Building.getBuildingByName(building), new Integer(floor));
		return getReply(table);
	}

	private String getReply(List<RoomTimeTable> roomTimeTableList){
		StringBuffer buffer = new StringBuffer();
		for (RoomTimeTable table : roomTimeTableList) {
			buffer.append(tableToText(table)).append("\n\n");
		}
		return new String(buffer);
	}

	private String getReply(RoomTimeTable roomTimeTable){
		return singleToText(roomTimeTable);
	}


	private boolean isParamValid(Building building, int floor) {
		if (floor < 1){
			return false;
		}
		if ((building == Building.SCIENCE)&& (floor< 6)){
			return true;
		}
		return (building == Building.MAIN) && (floor < 10);
	}

	private String tableToText(RoomTimeTable roomTimeTable) {
		Room room = roomTimeTable.getRoom();
		List<CourseTimeTable> courseTimeTable = roomTimeTable.getCourseTimeTable();
		StringBuilder builder = new StringBuilder(room.getName()).append(":\n");
		if (Objects.isNull(courseTimeTable) || courseTimeTable.size() == 0){
			builder.append("今天没课");
		}
		else {
			builder.append("第");
			for (CourseTimeTable table : courseTimeTable) {
				builder.append(table.getOrder()).append(" ");
			}
			builder.append("节有课 ");
		}
		return new String(builder);
	}

	private String singleToText(RoomTimeTable roomTimeTable) {
		StringBuilder sb = new StringBuilder(roomTimeTable.getRoom().getName());
		List<CourseTimeTable> timeTable = roomTimeTable.getCourseTimeTable();
		if (Objects.isNull(timeTable)){
			sb.append("今天没课");
			return new String(sb);
		}
		for (CourseTimeTable courseTimeTable : timeTable) {

			Course course = courseService.getCourseById(courseTimeTable.getCourse());
			if(Objects.isNull(course)){
				continue;
			}
			sb.append("\n").append("第").append(courseTimeTable.getOrder()).append("节： ");
			sb.append(course.getName());
			sb.append('\n');
		}

		return new String(sb);
	}
}
