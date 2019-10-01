package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {

	FASHION(0, "패션", "패션 스타일"),
	BEAUTY(1, "뷰티", "뷰티"),
	FOOD(2, "식품", "식품"),
	INTERIOR(3, "인테리어", "인테리어"),
	ELECTRONICS(4, "전자제품", "전자제품"),
	;
	
	private Integer id;
	private String title;
	private String description;
}

