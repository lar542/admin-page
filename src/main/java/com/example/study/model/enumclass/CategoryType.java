package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {

	COMPUTER(0, "컴퓨터-전자제품", "컴퓨터-전자제품"),
	CLOTHING(1, "의류", "의류"),
	MULTI_SHOP(2, "멀티샵", "멀티샵"),
	INTERIOR(3, "인테리어", "인테리어"),
	FOOD(4, "식품", "식품"),
	SPORTS(5, "스포츠", "스포츠"),
	SHOPPING_MALL(6, "쇼핑몰", "쇼핑몰"),
	DUTY_FREE(7, "면세점", "면세점"),
	BEAUTY(8, "화장", "화장")
	;
	
	private Integer id;
	private String title;
	private String description;
}

