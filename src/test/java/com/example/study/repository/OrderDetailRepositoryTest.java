package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.enumclass.OrderDetailStatus;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Test
	public void create() {
		OrderDetail orderDetail = OrderDetail.builder()
//				.orderGroupId(1L) //장바구니
//				.itemId(1L) //상품
//				.status(OrderDetailStatus.COMPLETE)
				.arrivalDate(LocalDateTime.now().plusDays(2))
				.quantity(1)
				.totalPrice(BigDecimal.valueOf(900000))
				.build();
				
		OrderDetail newDetail = orderDetailRepository.save(orderDetail);
		Assert.assertNotNull(newDetail);
	}

}
