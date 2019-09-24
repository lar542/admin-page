package com.example.study.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.study.model.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Item extends BaseTimeEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Partner parter;
	
	private String status;
	
	private String name;
	
	private String title;
	
	private String content;
	
	private Integer price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
}
