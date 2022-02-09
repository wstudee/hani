package com.runHani.demo;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/")
  public String index() {
    return "demo project!!";
  }
  @GetMapping("/api/hello")
  public String hello(){
  return "RUN HANI 서버시간은 "+new Date() +"입니다. \n";
  }
  @GetMapping("/api/hi")
  public String hi(){
	  return "미라는 오늘부터 휴가야 \n";
  }

}


