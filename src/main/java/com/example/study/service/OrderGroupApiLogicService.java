package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.UserRepository;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
		OrderGroupApiRequest body = request.getData();
		
		OrderGroup orderGroup = OrderGroup.builder()
				.status(body.getStatus())
				.orderType(body.getOrderType())
				.revAddress(body.getRevAddress())
				.revName(body.getRevName())
				.paymentType(body.getPaymentType())
				.totalPrice(body.getTotalPrice())
				.totalQuantity(body.getTotalQuantity())
				.orderAt(LocalDateTime.now())
				.user(userRepository.getOne(body.getUserId()))
				.build();
		
		OrderGroup newOrderGroup = baseRepository.save(orderGroup);
		return Header.OK(response(newOrderGroup));
	}

	@Override
	public Header<OrderGroupApiResponse> read(Long id) {
		return baseRepository.findById(id)
				.map(this::response)
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
		OrderGroupApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
			.map(orderGroup -> {
				orderGroup
					.setStatus(body.getStatus())
					.setOrderType(body.getOrderType())
					.setRevAddress(body.getRevAddress())
					.setRevName(body.getRevName())
					.setPaymentType(body.getPaymentType())
					.setTotalPrice(body.getTotalPrice())
					.setTotalQuantity(body.getTotalQuantity())
					.setOrderAt(body.getOrderAt())
					.setArrivalDate(body.getArrivalDate())
					.setUser(userRepository.getOne(body.getUserId()));
				return orderGroup;
			})
			.map(updateOrderGroup -> baseRepository.save(updateOrderGroup))
			.map(newOrderGroup -> response(newOrderGroup))
			.map(Header::OK)
			.orElseGet(() -> Header.ERROR("데이터 없음"));
	}	

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(orderGroup -> {
					baseRepository.delete(orderGroup);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	@Override
	public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {
		Page<OrderGroup> orderGroups = baseRepository.findAll(pageable);
		
		List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroups.stream()
				.map(orderGroup -> response(orderGroup))
				.collect(Collectors.toList());
		
		return Header.OK(orderGroupApiResponseList);
	}

	public OrderGroupApiResponse response(OrderGroup orderGroup) {
		OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
				.id(orderGroup.getId())
				.status(orderGroup.getStatus())
				.orderType(orderGroup.getOrderType().getTitle())
				.revAddress(orderGroup.getRevAddress())
				.revName(orderGroup.getRevName())
				.paymentType(orderGroup.getPaymentType().getTitle())
				.totalPrice(orderGroup.getTotalPrice())
				.totalQuantity(orderGroup.getTotalQuantity())
				.orderAt(orderGroup.getOrderAt())
				.arrivalDate(orderGroup.getArrivalDate())
				.userId(orderGroup.getUser().getId())
				.build();
		return orderGroupApiResponse;
	}
}
