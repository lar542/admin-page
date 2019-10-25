package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {
	
	REGISTERED(0, "REGISTERED", "등록", "사용자 등록상태"), 
	UNREGISTERED(1, "UNREGISTERED", "해지", "사용자 해지상태")
	;
	
	private Integer id;
	private String key;
	private String title;
	private String description;
	
}
