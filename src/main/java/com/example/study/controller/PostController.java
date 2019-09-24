package com.example.study.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.model.SearchParam;

@RestController
@RequestMapping("/api")
public class PostController {

    //HTML <from> 혹은 ajax를 통해 post 요청
    //받는 데이터 형식 : json, xml, multipart-form, text-plain
    @PostMapping(value = "/postMethod") //produces = {"application/json"} : json 외에 받을 데이터 형식을 지정
    public SearchParam postMethod(@RequestBody SearchParam searchParam){ // http post body => data
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }
}
