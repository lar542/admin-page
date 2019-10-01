package com.example.study.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.enumclass.AdminUserRole;
import com.example.study.model.enumclass.AdminUserStatus;

public class AdminUserRepositoryTest extends StudyApplicationTests {

	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Test
	public void create() {
		AdminUser adminUser = AdminUser.builder()
				.account("AdminUser04")
				.password("AdminUser04")
				.status(AdminUserStatus.REGISTERED)
				.role(AdminUserRole.ADMIN)
				.build();
		
		AdminUser newAdminUser = adminUserRepository.save(adminUser);
		Assert.assertNotNull(newAdminUser);
		
		newAdminUser.setAccount("CHANGE");
		adminUserRepository.save(newAdminUser);
	}
}
