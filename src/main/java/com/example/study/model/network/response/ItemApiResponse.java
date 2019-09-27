package com.example.study.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.study.model.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiResponse {

    private Long id;
	
	private String status;
	
	private String name;
	
	private String title;
	
	private String content;
	
	private BigDecimal price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private List<OrderDetail> orderDetails;
	
	private Long partnerId;
}
