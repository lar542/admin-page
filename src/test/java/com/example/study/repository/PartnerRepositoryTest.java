package com.example.study.repository;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Partner;
import com.example.study.model.enumclass.PartnerStatus;

public class PartnerRepositoryTest extends StudyApplicationTests {

	@Autowired
	private PartnerRepository partnerRepository;
	
	@Test
	public void create() {
		String name = "Partner01";
		PartnerStatus status = PartnerStatus.REGISTERED;
		String address = "서울시 강남구";
		String callCenter = "070-1111-2222";
		String partnerNumber = "010-1234-1234";
		String businessNumber = "1234567890123";
		String ceoName = "홍길동";
		LocalDateTime registeredAt = LocalDateTime.now();
    	Long category_id = 1L;
    	
    	Partner partner = Partner.builder()
    			.name(name)
    			.status(status)
    			.address(address)
    			.callCenter(callCenter)
    			.partnerNumber(partnerNumber)
    			.businessNumber(businessNumber)
    			.ceoName(ceoName)
    			.registeredAt(registeredAt)
//    			.category_id(category_id)
    			.build();
    	
    	Partner newPartner = partnerRepository.save(partner);
    	Assert.assertNotNull(newPartner);
    	Assert.assertEquals(partner.getName(), newPartner.getName());
	}
}
