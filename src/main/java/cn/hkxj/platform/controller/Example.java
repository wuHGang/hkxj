package cn.hkxj.platform.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;

@Controller
public class Example {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);

		new Object();
	}
}


