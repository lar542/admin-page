package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Category;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {

}
