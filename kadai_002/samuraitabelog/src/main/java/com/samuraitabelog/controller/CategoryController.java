package com.samuraitabelog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samuraitabelog.entity.Category;
import com.samuraitabelog.form.CategoryEditForm;
import com.samuraitabelog.form.CategoryRegisterForm;
import com.samuraitabelog.repository.CategoryRepository;
import com.samuraitabelog.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;
	
	public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService){
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Category> category = categoryRepository.findAll();
		
		model.addAttribute("category", category);
		
		return "admin/category/index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("categoryRegisterForm", new CategoryRegisterForm());
		
		return "admin/category/register";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "admin/category/register";
		}
		
		categoryService.create(categoryRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "カテゴリを登録しました。");
		
		return "redirect:/admin/category";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Category category = categoryRepository.getReferenceById(id);
		CategoryEditForm categoryEditForm = new CategoryEditForm(category.getId(), category.getName());
		
		model.addAttribute("categoryEditForm", categoryEditForm);
		
		return "admin/category/edit";
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated CategoryEditForm categoryEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
            return "admin/category/edit";
        }
		
		categoryService.update(categoryEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "カテゴリを編集しました。");
		
		return "redirect:/admin/category";
	}
	
	@PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
        categoryRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを削除しました。");
        
        return "redirect:/admin/category";
    }
	
}
