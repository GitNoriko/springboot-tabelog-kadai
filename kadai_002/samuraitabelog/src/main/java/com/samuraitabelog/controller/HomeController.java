package com.samuraitabelog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.samuraitabelog.entity.Category;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.repository.CategoryRepository;
import com.samuraitabelog.repository.StoreRepository;

@Controller
public class HomeController {
	
	private final StoreRepository storeRepository; 
	private final CategoryRepository categoryRepository;
    
    public HomeController(StoreRepository storeRepository, CategoryRepository categoryRepository) {
        this.storeRepository = storeRepository;    
        this.categoryRepository = categoryRepository;
    }
	
	@GetMapping("/")
	public String index(Model model) {
		List<Store> newStores = storeRepository.findAllByOrderByCreatedAtDesc();
		List<Category> category = categoryRepository.findAll();
		
        model.addAttribute("newStores", newStores);
        model.addAttribute("category", category);
		
		return "index";
	}
}
