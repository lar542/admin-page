package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;

public class CategoryRepositoryTest extends StudyApplicationTests {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
//	@Test
	public void create() {
		Category category = Category.builder()
				.type("COMPUTER")
				.title("컴퓨터")
				.createdAt(LocalDateTime.now())
				.createdBy("AdminServer")
				.build();
		
		Category newCategory = categoryRepository.save(category);
		
		Assert.assertNotNull(newCategory);
		Assert.assertEquals(category.getType(), newCategory.getType());
		Assert.assertEquals(category.getTitle(), newCategory.getTitle());
	}
	
	@Test
	public void read() {
		String type = "COMPUTER";
		Optional<Category> category = categoryRepository.findByType(type);
		
		category.ifPresent(c -> {
			Assert.assertEquals(c.getType(), type);
		});
	}
}
