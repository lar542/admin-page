package com.example.study.sampledata;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.model.enumclass.CategoryType;
import com.example.study.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategorySample extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void createSample(){
        List<CategoryType> category = Arrays.asList(
        		CategoryType.COMPUTER, 
        		CategoryType.CLOTHING, 
        		CategoryType.MULTI_SHOP, 
        		CategoryType.INTERIOR,
        		CategoryType.FOOD,
        		CategoryType.SPORTS,
        		CategoryType.SHOPPING_MALL,
        		CategoryType.DUTY_FREE,
        		CategoryType.BEAUTY
        		);
        List<String> title = Arrays.asList("컴퓨터-전자제품","의류","멀티샵","인테리어","음식","스포츠","쇼핑몰","면세점","화장");

        for(int i = 0; i < category.size(); i++){
            CategoryType c = category.get(i);
            String t = title.get(i);
            Category create = Category.builder().type(c).title(t).build();
            categoryRepository.save(create);
        }


    }

}
