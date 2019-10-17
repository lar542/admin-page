package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupStatus {

	ORDERING(0, "주문완료", "주문완료상태"),
	CONFIRM(1, "결제완료", "결제완료상태"),
	COMPLETE(2, "배송완료", "배송완료상태"),
	;
	
	private Integer id;
	private String title;
	private String description;
}
