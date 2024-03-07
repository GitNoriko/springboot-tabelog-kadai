package com.samuraitabelog.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.form.StoreEditForm;
import com.samuraitabelog.form.StoreRegisterForm;
import com.samuraitabelog.repository.CategoryRepository;
import com.samuraitabelog.repository.StoreRepository;
import com.samuraitabelog.service.StoreService;

@Controller
@RequestMapping("/admin/stores")
public class AdminStoreController {
	private final StoreRepository storeRepository;
	private final CategoryRepository categoryRepository;
	private final StoreService storeService;
	
	public AdminStoreController(StoreRepository storeRepository, CategoryRepository categoryRepository, StoreService storeService) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
		this.storeService = storeService;
	}
	
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		Page<Store> store = storeRepository.findAll(pageable);
		
		model.addAttribute("store", store);
		
		return "admin/stores/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);
		
		model.addAttribute("store", store);
		
		return "admin/stores/show";
	}
	
	@GetMapping("/register")
    public String register(Model model) {
		List<Category> category = categoryRepository.findAll();
		
        model.addAttribute("storeRegisterForm", new StoreRegisterForm());
        model.addAttribute("category", category);
        return "admin/stores/register";
    }
	
	@PostMapping("/create")
    public String create(@ModelAttribute @Validated StoreRegisterForm storeRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
        if (bindingResult.hasErrors()) {
            return "admin/stores/register";
        }
        
        storeService.create(storeRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");    
        
        return "redirect:/admin/stores";
    }
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		List<Category> category = categoryRepository.findAll();
		Store store = storeRepository.getReferenceById(id);
		String imageName = store.getImageName();
		StoreEditForm storeEditForm = new StoreEditForm(store.getId(), store.getName(), null, store.getDescription(), store.getLowestPrice(), store.getHighestPrice(), store.getPostalCode(), store.getAddress(), store.getOpeningTime(), store.getClosingTime(), store.getRegularHolidays(), store.getSeatingCapacity(), store.getCategory().getId());
		
		model.addAttribute("category", category);
		model.addAttribute("imageName", imageName);
		model.addAttribute("storeEditForm", storeEditForm);
		
		return "admin/stores/edit";
	}
	
	@PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
        storeRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");
        
        return "redirect:/admin/stores";
    }
	
	@PostMapping("/{id}/update")
    public String update(@ModelAttribute @Validated StoreEditForm storeEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
        if (bindingResult.hasErrors()) {
            return "admin/stores/edit";
        }
        
        storeService.update(storeEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");
        
        return "redirect:/admin/stores";
    }
}