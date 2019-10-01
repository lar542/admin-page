package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserRole {

	ADMIN(0, "관리자", "관리자 계정"),
	PARTNER(1, "파트너사", "파트너사 계정")
	;
	
	private Integer id;
	private String title;
	private String description;
}

