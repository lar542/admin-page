package com.example.study.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.enumclass.AdminUserRole;
import com.example.study.model.enumclass.AdminUserStatus;
import com.example.study.model.enumclass.ItemStatus;
import com.example.study.model.network.response.ItemApiResponse.ItemApiResponseBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiResponse {

	private Long id;
	
	private String account;
	
	private String password;
	
	private AdminUserStatus status;
	
	private AdminUserRole role;
	
	private LocalDateTime lastLoginAt;
	
	private LocalDateTime passwordUpdatedAt;
	
	private int loginFailCount;
	
	private LocalDateTime registeredAt;
    
    private LocalDateTime unregisteredAt;
}
