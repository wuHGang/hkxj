package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2019/3/19 22:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class ExcelServiceTest {

    @Resource
    private ExcelService excelService;


    @Test
    public void isDistinct(){
        System.out.println(excelService.lastIndexOfNum("14-16单周上"));;
    }

    @Test
    public void readExcel() {
        System.out.println(excelService.parseBuilding("科401（高层）"));
        System.out.println(excelService.parseBuilding("科N302"));
        System.out.println(excelService.parseBuilding("图书馆S4004"));
        System.out.println(excelService.parseBuilding("N10播放室"));
//        excelService.readExcel();
    }
}