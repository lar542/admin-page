package com.example.study.model.network.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클라이언트에서 요청으로 받는 데이터(json body)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiReuqest {

	private Long id;
    
    private String account;
    
    private String password; //요청일 때 평문으로 들어옴
    
    private String status;
    
    private String email;
    
    private String phoneNumber;
    
    private LocalDateTime registeredAt;
    
    private LocalDateTime unregisteredAt;
}
