package com.example.study.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.study.model.enumclass.ItemStatus;
import com.example.study.model.enumclass.PartnerStatus;
import com.example.study.service.AdminMenuService;
import com.example.study.service.CategoryApiLogicService;

@Controller
@RequestMapping("")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;
    
    @Autowired
    private CategoryApiLogicService categoryApiLogicService; 

    @RequestMapping(path = {""})
    public ModelAndView index() {
        return new ModelAndView("/pages/main")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }

    @RequestMapping("/category")
    public ModelAndView category() {
    	return new ModelAndView("/pages/category")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "category")
    			;
    }
    
    @RequestMapping("/partner")
    public ModelAndView partner() {
    	PartnerStatus[] test = PartnerStatus.class.getEnumConstants();
    	return new ModelAndView("/pages/partner")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "partner")
    			.addObject("statusList", PartnerStatus.class.getEnumConstants())
    			.addObject("categoryList", categoryApiLogicService.getList())
    			;
    }
    
    @RequestMapping("/item")
    public ModelAndView item() {
    	return new ModelAndView("/pages/item")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "item")
    			.addObject("statusList", ItemStatus.class.getEnumConstants())
    			;
    }
    
    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user")
                ;
    }
    
    @RequestMapping("/user/{id}")
    public ModelAndView order(@PathVariable Long id) {
    	return new ModelAndView("/pages/order")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "order")
    			.addObject("userId", id)
    			;
    }
    
}
