package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;

public class OrderGroupResposotiryTest extends StudyApplicationTests {

	@Autowired
	private OrderGroupRepository orderGroupRepository;
	
	@Test
	public void create() {
		OrderGroup orderGroup = OrderGroup.builder()
//				.userId(3L)
//				.status("COMPLETE")
//				.orderType("ALL")
				.revAddress("서울시 강남구")
				.revName("홍길동")
//				.paymentType("CARD")
				.totalPrice(BigDecimal.valueOf(900000))
				.totalQuantity(1)
				.orderAt(LocalDateTime.now().minusDays(2))
				.arrivalDate(LocalDateTime.now())
				.createdAt(LocalDateTime.now())
				.createdBy("AdminServer")
				.build();
	
		OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
		Assert.assertNotNull(newOrderGroup);
	}
}
