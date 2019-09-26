package com.example.study.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiReuqest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;

@Service
public class UserApiLogicService implements CrudInterface<UserApiReuqest, UserApiResponse>{

	@Autowired
	private UserRepository userRepository;

	@Override
	public Header<UserApiResponse> create(Header<UserApiReuqest> request) {
		//1. request data를 가져옴
		UserApiReuqest userApiReuqest = request.getData();
		
		//2. User 생성
		User user = User.builder()
				.account(userApiReuqest.getAccount())
				.password(userApiReuqest.getPassword())
				.status("REGISTERED")
				.phoneNumber(userApiReuqest.getPhoneNumber())
				.email(userApiReuqest.getEmail())
				.registeredAt(LocalDateTime.now())
				.build();
		
		User newUser = userRepository.save(user);
		
		//3. 생성된 데이터를 UserApiResponse로 만들어 리턴
		return response(newUser);
	}

	@Override
	public Header<UserApiResponse> read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header<UserApiResponse> update(Header<UserApiReuqest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * User -> UserApiResponse
	 * @param user
	 * @return 
	 */
	private Header<UserApiResponse> response(User user){
		UserApiResponse userApiResponse = UserApiResponse.builder()
				.id(user.getId())
				.account(user.getAccount())
				.password(user.getPassword()) //todo 암호화, 길이
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.status(user.getStatus())
				.registeredAt(user.getRegisteredAt())
				.unregisteredAt(user.getUnregisteredAt())
				.build();
		
		return Header.OK(userApiResponse);
	}
}
