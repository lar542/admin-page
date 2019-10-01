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
import com.example.study.model.enumclass.ItemStatus;

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
public class Item extends BaseTimeEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private ItemStatus status;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(length = 50)
	private String brandName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;
	
	@ManyToOne
	private Partner partner;
	
}
