package com.example.study.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class AdminUser {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 12)
	private String account;
	
	@Column(length = 100)
	private String password;
	
	@Column(length = 50)
	private String status;
	
	@Column(length = 50)
	private String role;
	
	private LocalDateTime lastLoginAt;
	
	private LocalDateTime passwordUpdatedAt;
	
	private int loginFailCount;
	
	@CreatedDate
	private LocalDateTime registeredAt;
    
    private LocalDateTime unregisteredAt;
    
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @CreatedBy
	private String createdBy;
    
    @LastModifiedDate
	private LocalDateTime updatedAt;
    
	@LastModifiedBy
	private String updatedBy;
	
}
