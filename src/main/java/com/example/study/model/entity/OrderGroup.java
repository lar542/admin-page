package com.example.study.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.study.model.BaseTimeEntity;
import com.example.study.model.enumclass.OrderGroupStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class OrderGroup extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private OrderGroupStatus status;
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String revAddress;
	
	@Column(length = 50)
	private String revName;
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	private Integer totalQuantity;
	
	private LocalDateTime orderAt;
	
	private LocalDateTime arrivalDate;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "orderGroup", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
	
}
