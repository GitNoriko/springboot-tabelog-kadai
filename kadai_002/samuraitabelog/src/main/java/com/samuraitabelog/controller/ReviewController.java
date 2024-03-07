package com.samuraitabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.samuraitabelog.entity.Review;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;
import com.samuraitabelog.form.ReviewEditForm;
import com.samuraitabelog.form.ReviewRegisterForm;
import com.samuraitabelog.repository.ReviewRepository;
import com.samuraitabelog.repository.StoreRepository;
import com.samuraitabelog.security.UserDetailsImpl;
import com.samuraitabelog.service.ReviewService;

@Controller
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {
	private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository; 
    private final ReviewService reviewService; 
    
    public ReviewController(ReviewRepository reviewRepository, StoreRepository storeRepository, ReviewService reviewService) {        
        this.reviewRepository = reviewRepository;
        this.storeRepository = storeRepository;
        this.reviewService = reviewService;
    }
    
    @GetMapping
    public String index(@PathVariable(name = "storeId") Integer storeId, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, Model model) {
        Store store = storeRepository.getReferenceById(storeId);
        Page<Review> reviewPage = reviewRepository.findAllByStoreOrderByCreatedAtDesc(store, pageable);       
                    
        model.addAttribute("store", store); 
        model.addAttribute("reviewPage", reviewPage);                
        
        return "reviews/index";
    }
    
    @GetMapping("/register")
    public String register(@PathVariable(name = "storeId") Integer storeId, Model model) { 
        Store store = storeRepository.getReferenceById(storeId);
        
        model.addAttribute("store", store);
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
        
        return "reviews/register";
    }
    
    @PostMapping("/create")
    public String create(@PathVariable(name = "storeId") Integer storeId,
                         @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                         @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model)
    {    
        Store store = storeRepository.getReferenceById(storeId);
        User user = userDetailsImpl.getUser();
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("store", store);
            return "reviews/register";
        }        
        
        reviewService.create(store, user, reviewRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");    
        
        return "redirect:/stores/{storeId}";
    }
    
    @GetMapping("/{reviewId}/edit")
    public String edit(@PathVariable(name = "storeId") Integer storeId, @PathVariable(name = "reviewId") Integer reviewId, Model model) {
        Store store = storeRepository.getReferenceById(storeId);
        Review review = reviewRepository.getReferenceById(reviewId);
        
        ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getScore(), review.getContent());
        
        model.addAttribute("store", store);
        model.addAttribute("review", review);
        model.addAttribute("reviewEditForm", reviewEditForm);
        
        return "reviews/edit";
    }
    
    @PostMapping("/{reviewId}/update")
    public String update(@PathVariable(name = "storeId") Integer storeId,
                         @PathVariable(name = "reviewId") Integer reviewId,
                         @ModelAttribute @Validated ReviewEditForm reviewEditForm,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) 
    {    
        Store store = storeRepository.getReferenceById(storeId);
        Review review = reviewRepository.getReferenceById(reviewId);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("store", store);
            model.addAttribute("review", review);
            return "reviews/edit";
        }
        
        reviewService.update(reviewEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
        
        return "redirect:/stores/{storeId}";
    }    
    
    @PostMapping("/{reviewId}/delete")
    public String delete(@PathVariable(name = "reviewId") Integer reviewId, RedirectAttributes redirectAttributes) {        
        reviewRepository.deleteById(reviewId);
                
        redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
        
        return "redirect:/stores/{storeId}";
    }
}