package com.example.study.model.network.request;

import java.time.LocalDateTime;

import com.example.study.model.enumclass.AdminUserRole;
import com.example.study.model.enumclass.AdminUserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiRequest {

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
