package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.AdminUser;
import com.example.study.model.enumclass.AdminUserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

	@Override
	public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
		return Optional.ofNullable(request.getData())
				.map(body -> {
					AdminUser adminUser = AdminUser.builder()
							.account(body.getAccount())
							.password(body.getPassword())
							.status(AdminUserStatus.REGISTERED)
							.role(body.getRole())
							.registeredAt(LocalDateTime.now())
							.build();
					return adminUser;
				})
				.map(newAdminUser -> baseRepository.save(newAdminUser))
				.map(newAdminUser -> response(newAdminUser))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<AdminUserApiResponse> read(Long id) {
		return baseRepository.findById(id)
				.map(adminUser -> response(adminUser))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
		AdminUserApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
				.map(findEntity -> {
					findEntity
						.setAccount(body.getAccount())
						.setPassword(body.getPassword())
						.setStatus(body.getStatus())
						.setRole(body.getRole())
						.setRegisteredAt(body.getRegisteredAt())
						.setUnregisteredAt(body.getUnregisteredAt());
					return findEntity;
				})
				.map(updateEntity -> baseRepository.save(updateEntity))
				.map(newEntity -> response(newEntity))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(adminUser -> {
					baseRepository.delete(adminUser);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	@Override
	public Header<List<AdminUserApiResponse>> search(Pageable pageable) {
		Page<AdminUser> adminUsers = baseRepository.findAll(pageable);
		
		List<AdminUserApiResponse> adminUserApiResponseList = adminUsers.stream()
				.map(adminUser -> response(adminUser))
				.collect(Collectors.toList());
		
		return Header.OK(adminUserApiResponseList);
	}

	private AdminUserApiResponse response(AdminUser adminUser){
		AdminUserApiResponse body = AdminUserApiResponse.builder()
				.id(adminUser.getId())
				.account(adminUser.getAccount())
				.password(adminUser.getPassword())
				.status(adminUser.getStatus())
				.role(adminUser.getRole())
				.lastLoginAt(adminUser.getLastLoginAt())
				.passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
				.loginFailCount(adminUser.getLoginFailCount())
				.registeredAt(adminUser.getRegisteredAt())
				.unregisteredAt(adminUser.getUnregisteredAt())
				.build();
		return body;
	}

}
