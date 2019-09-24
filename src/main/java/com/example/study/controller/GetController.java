package com.example.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.model.SearchParam;

@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/getMethod")
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        System.out.println("id : " + id);
        System.out.println("password : " + pwd);
        return id + pwd;
    }

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.toString());
        return searchParam; //json 응답 : Spring boot는 기본적으로 잭슨 라이브러리 내장
    }
}
