package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupStatus {

	WAITING(0, "결제대기", "장바구니에 저장한 상태"), 
	COMPLETE(1, "결제완료", "결제완료상태")
	;
	
	private Integer id;
	private String title;
	private String description;
}
