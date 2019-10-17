package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

	CARD(0, "카드", "카드 결제"), 
	BANK_TRANSFER(1, "무통장", "무통장 입금"),
	CHECK_CARD(2, "체크카드", "체크카드")
	;
	
	private Integer id;
	private String title;
	private String description;
}
