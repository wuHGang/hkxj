package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.example.RoomExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/10/29
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomMapperTest {
	@Autowired
	private RoomMapper roomMapper;

	@Test
	public void selectByExample() {
		RoomExample roomExample = new RoomExample();
		roomExample
				.createCriteria()
				.andAreaEqualTo(Building.SCIENCE)
				.andFloorEqualTo(1);
		for (Room room : roomMapper.selectByExample(roomExample)) {
			System.out.println(room);
		}

	}

	@Test
	public void selectByPrimaryKey() {
		List<Integer> integers = roomMapper.selectCourseTimeTableID(1);
		log.info(integers.toString());
	}
}