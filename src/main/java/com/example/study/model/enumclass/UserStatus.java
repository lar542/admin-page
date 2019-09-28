package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
	
	//index, 타이틀, 상세설명
	REGISTERED(0, "등록상태", "파트너사 등록상태"), 
	UNREGISTERED(1, "해지", "파트너사 해지상태");
	
	private Integer id;
	private String title;
	private String description;
}
