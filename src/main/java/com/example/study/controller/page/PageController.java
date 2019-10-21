package com.example.study.controller.page;

import com.example.study.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;

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
    	return new ModelAndView("/pages/partner")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "partner")
    			;
    }
    
    @RequestMapping("/item")
    public ModelAndView item() {
    	return new ModelAndView("/pages/item")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "item")
    			;
    }
    
    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user")
                ;
    }
    
    @RequestMapping("/order")
    public ModelAndView order() {
    	return new ModelAndView("/pages/order")
    			.addObject("menuList", adminMenuService.getAdminMenu())
    			.addObject("code", "order")
    			;
    }
    
}
