package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TimeTableServiceTest {
	@Resource(name = "timeTableService")
	private TimeTableService timeTableService;
	@Resource
	private AppSpiderService appSpiderService;

	@Test
	public void getLessonFromApp(){


		FileInputStream fis;
		Workbook wb;
		try {
			fis=new FileInputStream("G:\\2016+2017.xls");
			wb=new HSSFWorkbook(fis);
			Sheet sheet=wb.getSheetAt(0);
			int rowNum=sheet.getLastRowNum();
			Student student=new Student();
			Set<String> classSet=new HashSet<>();
			Set<String> errorClassSet=new HashSet<>();
			for(int i =234;i<=234;i++){
				log.info("start:"+i);
				Row row=sheet.getRow(i);
				String className=String.valueOf(row.getCell(6));
				if(!classSet.contains(className)){
					Cell cell = row.getCell(1);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					student.setAccount(Integer.parseInt(String.valueOf(cell)));
//					student.setAccount(2017024556);
					student.setPassword("1");
					try {
						appSpiderService.getLessonFromApp(student);
						classSet.add(className);
						sheet.removeRow(row);
					} catch (Exception e){
						classSet.remove(className);
						errorClassSet.add(className);
						log.error(e+e.getMessage()+className+";"+student.getAccount());
					}
				}
				else continue;
			}
			for (String str : errorClassSet) {
				System.out.println(str);
			}
		}catch (Exception e){
			log.error(e+e.getMessage());
		}





	}
//
//	@Test
//	public void temporary(){
//		Student student=new Student();
//		student.setAccount(2016025910);
//		appSpiderService.getScheduleFromApp(student);
//	}

//	@Test
//	public void getTodayTimeTable() {
////		HashSet<String> roomSet = new HashSet<>();
////		int count = 0;
////		for (CourseTimeTable table : timeTableService.getTimeTableFromDB(5)) {
////			roomSet.add(table.getRoom().getName());
////			count += 1;
////		}
////		System.out.println(roomSet.size());
////		System.out.println(count);
//	}

//	@Test
//	public void getRoomTimeTableByBuildingAndFloor() {
////		for (RoomTimeTable table : timeTableService.getRoomTimeTableByBuildingAndFloor(Building.SCIENCE, 4)) {
////			System.out.println(table.toText()+'\n');
////		}
//
//	}
}