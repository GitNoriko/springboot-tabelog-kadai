package com.samuraitabelog.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuraitabelog.entity.Category;
import com.samuraitabelog.entity.Favorite;
import com.samuraitabelog.entity.Review;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;
import com.samuraitabelog.form.ReservationInputForm;
import com.samuraitabelog.repository.CategoryRepository;
import com.samuraitabelog.repository.FavoriteRepository;
import com.samuraitabelog.repository.ReviewRepository;
import com.samuraitabelog.repository.StoreRepository;
import com.samuraitabelog.security.UserDetailsImpl;
import com.samuraitabelog.service.FavoriteService;
import com.samuraitabelog.service.ReviewService;

@Controller
@RequestMapping("/stores")
public class StoreController {
	private final StoreRepository storeRepository;
	private final CategoryRepository categoryRepository;
	private final FavoriteRepository favoriteRepository;
	private final FavoriteService favoriteService;
	private final ReviewRepository reviewRepository;   
    private final ReviewService reviewService;
	
	public StoreController(StoreRepository storeRepository, CategoryRepository categoryRepository, FavoriteRepository favoriteRepository, FavoriteService favoriteService, ReviewRepository reviewRepository, ReviewService reviewService) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
		this.favoriteRepository = favoriteRepository;
		this.favoriteService = favoriteService;
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "category", required = false) Integer categoryId,
                        @RequestParam(name = "lowest_price", required = false) Integer lowest_price, 
                        @RequestParam(name = "highest_price", required = false) Integer highest_price,
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model)
	{
		Page<Store> storePage;
		List<Category> category = categoryRepository.findAll();
		
		if(categoryId != null) {
			storePage = storeRepository.findByCategoryId(categoryId, pageable);	
		}else {
			storePage = storeRepository.findAll(pageable);
		}
		
		model.addAttribute("storePage", storePage);
		model.addAttribute("categoryId", categoryId);
        model.addAttribute("lowest_price", lowest_price);
        model.addAttribute("highest_price", highest_price);
        model.addAttribute("category", category);
		
		return "stores/index";
	}
	
	@GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Store store = storeRepository.getReferenceById(id);
        Favorite favorite = null;
        boolean isFavorite = false;
        boolean hasUserAlreadyReviewed = false;
        
        if(userDetailsImpl != null) {
        	User user = userDetailsImpl.getUser();
        	isFavorite = favoriteService.isFavorite(store, user);
        	hasUserAlreadyReviewed = reviewService.hasUserAlreadyReviewed(store, user);
        	if(isFavorite) {
        		favorite = favoriteRepository.findByStoreAndUser(store, user);
        	}
        }
        
        List<Review> newReviews = reviewRepository.findAllByStoreOrderByCreatedAtDesc(store);        
        long totalReviewCount = reviewRepository.countByStore(store);
        
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        model.addAttribute("favorite", favorite);
        model.addAttribute("store", store);        
        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("hasUserAlreadyReviewed", hasUserAlreadyReviewed);
        model.addAttribute("newReviews", newReviews);        
        model.addAttribute("totalReviewCount", totalReviewCount); 
        
        return "stores/show";
    }
}
