package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiReuqest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;

@Service
public class UserApiLogicService extends BaseService<UserApiReuqest, UserApiResponse, User>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderGroupApiLogicService orderGroupApiLogicService;
	
	@Autowired
	private ItemApiLogicService itemApiLogicService;
	
	@Override
	public Header<UserApiResponse> create(Header<UserApiReuqest> request) {
		//1. request data를 가져옴
		UserApiReuqest userApiReuqest = request.getData();
		
		//2. User 생성
		User user = User.builder()
				.account(userApiReuqest.getAccount())
				.password(userApiReuqest.getPassword())
				.status(UserStatus.REGISTERED)
				.phoneNumber(userApiReuqest.getPhoneNumber())
				.email(userApiReuqest.getEmail())
				.registeredAt(LocalDateTime.now())
				.build();
		
		User newUser = baseRepository.save(user);
		
		//3. 생성된 데이터를 UserApiResponse로 만들어 리턴
		return Header.OK(response(newUser));
	}

	@Override
	public Header<UserApiResponse> read(Long id) {
		return baseRepository.findById(id)
			.map(user -> response(user)) //user객체가 있으면 map으로 response 메소드 적용
//			.map(userApiResponse -> Header.OK(userApiResponse))
			.map(Header::OK)
			.orElseGet( //user가 없다면
				() -> Header.ERROR("데이터 없음")
			);
	}

	@Override
	public Header<UserApiResponse> update(Header<UserApiReuqest> request) {
		UserApiReuqest userApiReuqest = request.getData();
		
		return baseRepository.findById(userApiReuqest.getId())
			.map(user -> { //user 객체가 있으면
				//set
				user
					.setAccount(userApiReuqest.getAccount())
					.setPassword(userApiReuqest.getPassword())
					.setStatus(userApiReuqest.getStatus())
					.setPhoneNumber(userApiReuqest.getPhoneNumber())
					.setEmail(userApiReuqest.getEmail())
					.setRegisteredAt(userApiReuqest.getRegisteredAt())
					.setUnregisteredAt(userApiReuqest.getUnregisteredAt());
				return user;
			})
			.map(user -> baseRepository.save(user))		//update -> 새로운 user 객체 리턴
			.map(updateUser -> response(updateUser)) 	//userApiResponse
			.map(Header::OK)
			.orElseGet(() -> Header.ERROR("데이터 없음"));	//위에서 user 객체가 한 번이라도 없을 때
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(user -> {
					baseRepository.delete(user);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	@Override
	public Header<List<UserApiResponse>> search(Pageable pageable) {
		Page<User> users = baseRepository.findAll(pageable);
		
		List<UserApiResponse> userApiResponseList = users.stream()
				.map(user -> response(user))
				.collect(Collectors.toList());
		
		return Header.OK(userApiResponseList);
	}
	
	@Override
	public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
		//user
		User user = userRepository.getOne(id);
		UserApiResponse userApiResponse = response(user);
		
		//orderGroup
		List<OrderGroup> orderGroupList = user.getOrderGroups();
		List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
				.map(orderGroup -> {
					OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup);
					
					//item api response
					List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetails().stream()
							.map(orderDetail -> orderDetail.getItem())
							.map(item -> itemApiLogicService.response(item))
							.collect(Collectors.toList());
					
					orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
					return orderGroupApiResponse;
				})
				.collect(Collectors.toList());
		
		userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
		
		UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
				.userApiResponse(userApiResponse)
				.build();
		
		return Header.OK(userOrderInfoApiResponse);
	}
	
	/**
	 * User -> UserApiResponse
	 * @param user
	 * @return 
	 */
	private UserApiResponse response(User user){
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
		
		return userApiResponse;
	}
	
}
