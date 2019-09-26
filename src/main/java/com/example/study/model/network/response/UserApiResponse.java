package com.example.study.model.network.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {

private String account;

	private Long id;
    
    private String password; //암호화하여 응답
    
    private String status;
    
    private String email;
    
    private String phoneNumber;
    
    private LocalDateTime registeredAt;
    
    private LocalDateTime unregisteredAt;
}
