package com.example.study.model.network.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.enumclass.ItemStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiRequest {

	private Long id;
	
	private ItemStatus status;
	
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
