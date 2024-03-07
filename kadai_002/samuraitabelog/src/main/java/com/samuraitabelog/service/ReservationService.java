package com.samuraitabelog.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuraitabelog.entity.Reservation;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;
import com.samuraitabelog.form.ReservationRegisterForm;
import com.samuraitabelog.repository.ReservationRepository;
import com.samuraitabelog.repository.StoreRepository;
import com.samuraitabelog.repository.UserRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;  
    private final StoreRepository storeRepository;  
    private final UserRepository userRepository;  
    
    public ReservationService(ReservationRepository reservationRepository, StoreRepository storeRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;  
        this.storeRepository = storeRepository;  
        this.userRepository = userRepository;  
    }    
    
    @Transactional
    public void create(ReservationRegisterForm reservationRegisterForm) { 
        Reservation reservation = new Reservation();
        Store store = storeRepository.getReferenceById(reservationRegisterForm.getStoreId());
        User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
        LocalDate checkinDate = LocalDate.parse(reservationRegisterForm.getCheckinDate());
        LocalTime checkinTime = LocalTime.parse(reservationRegisterForm.getCheckinTime());         
                
        reservation.setStore(store);
        reservation.setUser(user);
        reservation.setCheckinDate(checkinDate);
        reservation.setCheckinTime(checkinTime);
        reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
        
        reservationRepository.save(reservation);
    } 
}
