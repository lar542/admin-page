package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.enumclass.ItemStatus;

public class ItemRepositoryTest extends StudyApplicationTests {

	@Autowired
	private ItemRepository itemRepository;
	
//	@Test
	public void create() {
		Item item = Item.builder()
				.status(ItemStatus.REGISTERED)
				.name("삼성 노트북")
				.title("삼성 노트북 A100")
				.content("2019년 노트북입니다")
				.price(BigDecimal.valueOf(900000))
				.brandName("삼성")
				.registeredAt(LocalDateTime.now())
//				.partnerId(1L)
				.build();
		
		Item newItem = itemRepository.save(item);
		Assert.assertNotNull(newItem);
	}
	
//	@Test
	public void read() {
		Optional<Item> findItem = itemRepository.findById(1L);
		Assert.assertTrue(findItem.isPresent());
	}
	
	@Test
	public void 검색() {
		
	}
}
