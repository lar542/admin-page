package com.example.study.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.study.model.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGroup extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private User user;
	
	private String status;
	
	private String orderType;
	
	private String revAddress;
	
	private String revName;
	
	private String paymentType;
	
	private BigDecimal totalPrice;
	
	private Integer totalQuantity;
	
	private LocalDateTime orderAt;
	
	private LocalDateTime arrivalDate;
}
