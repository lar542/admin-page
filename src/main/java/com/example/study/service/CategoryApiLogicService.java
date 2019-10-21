package com.example.study.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.Category;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

	@Override
	public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
		return Optional.ofNullable(request.getData())
				.map(body -> {
					Category category = Category.builder()
							.type(body.getType())
							.title(body.getTitle())
							.build();
					return category;
				})
				.map(newEntity -> baseRepository.save(newEntity))
				.map(newEntity -> response(newEntity))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<CategoryApiResponse> read(Long id) {
		return baseRepository.findById(id)
				.map(findEntity -> response(findEntity))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
		CategoryApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
				.map(findEntity -> {
					findEntity
						.setType(body.getType())
						.setTitle(body.getTitle());
					return findEntity;
				})
				.map(updateEntity -> baseRepository.save(updateEntity))
				.map(newEntity -> response(newEntity))
				.map(Header::OK)
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(findEntity -> {
					baseRepository.delete(findEntity);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	@Override
	public Header<List<CategoryApiResponse>> search(Pageable pageable) {
		Page<Category> categorys = baseRepository.findAll(pageable);
		
		List<CategoryApiResponse> categoryApiResponseList = categorys.stream()
				.map(category -> response(category))
				.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(categorys.getTotalPages())
				.totalElements(categorys.getTotalElements())
				.currentPage(categorys.getNumber())
				.currentElements(categorys.getNumberOfElements())
				.build();
		
		return Header.OK(categoryApiResponseList, pagination);
	}

	private CategoryApiResponse response(Category category){
		CategoryApiResponse body = CategoryApiResponse.builder()
				.id(category.getId())
				.type(category.getType())
				.title(category.getTitle())
				.build();
		return body;
	}
}
