package com.example.study.model.entity;

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
public class Partner extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Category category;
	
	private String name;
	
	private String status;
	
	private String address;
	
	private String callCenter;
	
	private String partnerNumber;
	
	private String bussinessNumber;
	
	private String ceoName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
}
