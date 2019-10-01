package com.example.study.model.entity;

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
import com.example.study.model.enumclass.PartnerStatus;

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
public class Partner extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private PartnerStatus status;
	
	@Column(nullable = false, length = 100)
	private String address;
	
	@Column(length = 13)
	private String callCenter;
	
	@Column(length = 13)
	private String partnerNumber;
	
	@Column(length = 16)
	private String businessNumber;
	
	@Column(length = 20)
	private String ceoName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	@OneToMany(mappedBy = "partner", fetch = FetchType.LAZY)
	private List<Item> items;
	
	@ManyToOne
	private Category category;
	
}
