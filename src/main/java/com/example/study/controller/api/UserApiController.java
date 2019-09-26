package com.example.study.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiReuqest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //JAVA에서 간단하게 사용하는 로깅 시스템
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiReuqest, UserApiResponse> {

	@Autowired
	private UserApiLogicService userApiLogicService; 
	
	@Override
	@PostMapping("") // /api/user
	public Header<UserApiResponse> create(@RequestBody Header<UserApiReuqest> request) {
		log.info("{}", request);
		return userApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}") // /api/user/{id}
	public Header<UserApiResponse> read(@PathVariable Long id) {
		return null;
	}

	@Override
	@PutMapping("") // /api/user
	public Header<UserApiResponse> update(@RequestBody Header<UserApiReuqest> userApiReuqest) {
		return null;
	}

	@Override
	@DeleteMapping("{id}") // /api/user/{id}
	public Header delete(@PathVariable Long id) {
		return null;
	}
}
