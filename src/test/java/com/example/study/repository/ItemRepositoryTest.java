package com.example.study.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;

public class ItemRepositoryTest extends StudyApplicationTests {

	@Autowired
	private ItemRepository itemRepository;
	
//	@Test
	public void create() {
		Item item = new Item();
		item.setName("노트북");
		item.setPrice(100000);
		item.setContent("삼성 노트북");
		
		Item newItem = itemRepository.save(item);
		Assert.assertNotNull(newItem);
	}
	
	@Test
	public void read() {
		Optional<Item> findItem = itemRepository.findById(1L);
		Assert.assertTrue(findItem.isPresent());
	}
}
