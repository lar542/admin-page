package com.example.study.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.study.model.enumclass.OrderGroupStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {

	private Long id;
	
	private OrderGroupStatus status;
	
	private String orderType;
	
	private String revAddress;
	
	private String revName;
	
	private String paymentType;
	
	private BigDecimal totalPrice;
	
	private Integer totalQuantity;
	
	private LocalDateTime orderAt;
	
	private LocalDateTime arrivalDate;
	
	private Long userId;
	
	private List<ItemApiResponse> itemApiResponseList;
}
