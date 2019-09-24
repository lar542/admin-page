package com.example.study.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Test
	public void create() {
		OrderDetail orderDetail = new OrderDetail();
//		orderDetail.setItemId(1L);
//		orderDetail.setUserId(2L);
		
		OrderDetail newDetail = orderDetailRepository.save(orderDetail);
		Assert.assertNotNull(newDetail);
	}

}
