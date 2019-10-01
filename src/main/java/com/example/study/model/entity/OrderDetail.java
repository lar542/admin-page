package com.example.study.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.study.model.BaseTimeEntity;
import com.example.study.model.enumclass.OrderDetailStatus;

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
public class OrderDetail extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private OrderDetailStatus status;
	
	private LocalDateTime arrivalDate;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@ManyToOne
	private OrderGroup orderGroup;
	
	@ManyToOne
	private Item item;
	
}
