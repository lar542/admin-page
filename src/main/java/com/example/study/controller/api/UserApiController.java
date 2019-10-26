package com.example.study.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiReuqest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiReuqest, UserApiResponse, User> {

	@Autowired
	private UserApiLogicService userApiLogicService;
	
	@GetMapping("/{id}/orderInfo")
	public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
		return userApiLogicService.orderInfo(id);
	}
}
