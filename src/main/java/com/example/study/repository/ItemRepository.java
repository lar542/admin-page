package com.example.study.repository;

import org.springframework.boot.autoconfigure.web.ResourceProperties.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Page<Content> findAll(Specification<Content> spec, Pageable pageable);
}
