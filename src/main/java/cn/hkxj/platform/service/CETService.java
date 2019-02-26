package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.CETMapper;
import cn.hkxj.platform.pojo.CETStudent;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileInputStream;



@Service
@Slf4j
public class CETService {

    @Autowired
    CETMapper cetMapper;

    public  void readExcel(){
        FileInputStream fis=null;
        Workbook workbook=null;
        try {
            fis=new FileInputStream("G:\\cet.xls");
            workbook = new HSSFWorkbook(fis);
            Sheet sheet=workbook.getSheetAt(0);
            int rowNum=sheet.getLastRowNum();

            for(int i =2;i<rowNum;i++){
                Row row=sheet.getRow(i);
                    CETStudent cetStudent=new CETStudent();
                        cetStudent.setLevel(String.valueOf(row.getCell(0)));
                        cetStudent.setAccount(Integer.parseInt(String.valueOf(row.getCell(1))));
                        cetStudent.setName(String.valueOf(row.getCell(2)));
                        cetStudent.setClassRoom(String.valueOf(row.getCell(3)));
                        cetStudent.setExaminee(String.valueOf(row.getCell(4)));
                        cetMapper.insertCET(cetStudent);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public String getCETExaminee(Student student){
        CETStudent cetStudent=cetMapper.getCETStudentByAccount(student.getAccount());
        return cetStudent.getExaminee();
    }

}
