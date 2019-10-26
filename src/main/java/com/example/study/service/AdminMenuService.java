package com.example.study.service;

import com.example.study.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenu> getAdminMenu(){
        return Arrays.asList(
    		AdminMenu.builder().title("상품 카테고리 관리").url("/category").code("category").build(),
    		AdminMenu.builder().title("파트너사 관리").url("/partner").code("partner").build(),
    		AdminMenu.builder().title("상품 관리").url("/item").code("item").build(),
            AdminMenu.builder().title("고객 관리").url("/user").code("user").build()
        );

    }

}
