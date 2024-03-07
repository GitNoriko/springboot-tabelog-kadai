package com.samuraitabelog.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samuraitabelog.repository.ReservationRepository;
import com.samuraitabelog.repository.StoreRepository;
import com.samuraitabelog.repository.UserRepository;
import com.samuraitabelog.service.StripeService;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final StripeService stripeService;
    
    public AdminHomeController(UserRepository userRepository, StoreRepository storeRepository, ReservationRepository reservationRepository, StripeService stripeService) {
    	this.userRepository = userRepository;
    	this.storeRepository = storeRepository;
    	this.reservationRepository = reservationRepository;
    	this.stripeService = stripeService;
    }
    
    @GetMapping
    public String index(Model model) {
    	long totalMembers= userRepository.countByRoleNameInRoleFreeMemberOrRolePaidMember();
        long totalFreeMembers = userRepository.countByRole_Name("ROLE_FREE_MEMBER");
        long totalPaidMembers = userRepository.countByRole_Name("ROLE_PAID_MEMBER");;
        long totalshops = storeRepository.count();
        long totalReservations = reservationRepository.count();
        
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        long salesForThisMonth;
        
        try {
            salesForThisMonth = stripeService.getMonthlyTotalRevenue(startOfMonth);
        } catch (Exception e) {
            salesForThisMonth = 0;
        } 
        
        model.addAttribute("totalMembers", totalMembers);
        model.addAttribute("totalFreeMembers", totalFreeMembers);
        model.addAttribute("totalPaidMembers", totalPaidMembers);
        model.addAttribute("totalStores", totalshops);
        model.addAttribute("totalReservations", totalReservations);
        model.addAttribute("salesForThisMonth", salesForThisMonth);
    	
    	return "admin/index";
    }
}
