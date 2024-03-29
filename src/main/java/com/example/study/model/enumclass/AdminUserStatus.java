package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserStatus {

	REGISTERED(0, "등록", "관리자 계정 등록 상태"),
	UNREGISTERED(1, "해지", "관리자 계정 해지 상태")
	;
	
	private Integer id;
	private String title;
	private String description;
}
