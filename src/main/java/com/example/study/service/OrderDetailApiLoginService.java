package com.example.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderGroupRepository;

@Service
public class OrderDetailApiLoginService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

	@Autowired
	private OrderGroupRepository orderGroupRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
		return Optional.ofNullable(request.getData())
				.map(body -> {
					OrderDetail orderDetail = OrderDetail.builder()
							.status(body.getStatus())
							.arrivalDate(body.getArrivalDate())
							.quantity(body.getQuantity())
							.totalPrice(body.getTotalPrice())
							.orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
							.item(itemRepository.getOne(body.getItemId()))
							.build();
					return orderDetail;
				})
				.map(newEntity -> baseRepository.save(newEntity))
				.map(newEntity -> response(newEntity))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<OrderDetailApiResponse> read(Long id) {
		return baseRepository.findById(id)
				.map(findEntity -> response(findEntity))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		OrderDetailApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
				.map(findEntity -> {
					findEntity
						.setStatus(body.getStatus())
						.setArrivalDate(body.getArrivalDate())
						.setQuantity(body.getQuantity())
						.setTotalPrice(body.getTotalPrice())
						.setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
						.setItem(itemRepository.getOne(body.getItemId()));
					return findEntity;
				})
				.map(updateEntity -> baseRepository.save(updateEntity))
				.map(newEntity -> response(newEntity))
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(findEntity -> {
					baseRepository.delete(findEntity);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	private Header<OrderDetailApiResponse> response(OrderDetail orderDetail){
		OrderDetailApiResponse body = OrderDetailApiResponse.builder()
				.id(orderDetail.getId())
				.status(orderDetail.getStatus())
				.arrivalDate(orderDetail.getArrivalDate())
				.quantity(orderDetail.getQuantity())
				.totalPrice(orderDetail.getTotalPrice())
				.orderGroupId(orderDetail.getOrderGroup().getId())
				.itemId(orderDetail.getItem().getId())
				.build();
		return Header.OK(body);
	}
}
