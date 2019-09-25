package com.example.study.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Partner {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String status;
	
	@Column(nullable = false, length = 100)
	private String address;
	
	@Column(length = 13)
	private String callCenter;
	
	@Column(length = 13)
	private String partnerNumber;
	
	@Column(length = 16)
	private String businessNumber;
	
	@Column(length = 20)
	private String ceoName;
	
	@CreatedDate
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	@OneToMany(mappedBy = "partner", fetch = FetchType.LAZY)
	private List<Item> items;
	
	@ManyToOne
	private Category category;
	
	@CreatedDate
    private LocalDateTime createdAt;
    
    @CreatedBy
	private String createdBy;
    
    @LastModifiedDate
	private LocalDateTime updatedAt;
    
	@LastModifiedBy
	private String updatedBy;
	
}
