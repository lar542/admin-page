package com.example.study.model.network.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiResponse {

	private Long id;
	
	private String name;
	
	private String statusKey;
	
	private String statusTitle;
	
	private String address;
	
	private String callCenter;
	
	private String partnerNumber;
	
	private String businessNumber;
	
	private String ceoName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private Long categoryId;
	
	private String categoryTitle;
}
