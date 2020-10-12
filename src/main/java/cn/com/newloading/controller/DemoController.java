package cn.com.newloading.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(HttpServletRequest request){
        String s = "Hello World ！！！";
        
        return s;
    }
}
