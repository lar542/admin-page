package com.example.study.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.study.model.BaseTimeEntity;
import com.example.study.model.enumclass.AdminUserRole;
import com.example.study.model.enumclass.AdminUserStatus;

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
public class AdminUser extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 12)	
	private String account;
	
	@Column(length = 100)
	private String password;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdminUserStatus status;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdminUserRole role;
	
	private LocalDateTime lastLoginAt;
	
	private LocalDateTime passwordUpdatedAt;
	
	private int loginFailCount;
	
	private LocalDateTime registeredAt;
    
    private LocalDateTime unregisteredAt;
	
}
