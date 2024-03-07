package com.samuraitabelog.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	private Integer storeId;
    
    private Integer userId;    
        
    private String checkinDate;    
        
    private String checkinTime;    
    
    private Integer numberOfPeople;
    

}
