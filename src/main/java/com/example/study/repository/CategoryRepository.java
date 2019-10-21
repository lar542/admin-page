package com.example.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.Category;
import com.example.study.model.enumclass.CategoryType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByType(CategoryType type);
	
}
