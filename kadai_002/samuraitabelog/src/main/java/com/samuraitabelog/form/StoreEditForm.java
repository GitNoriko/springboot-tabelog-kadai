package com.samuraitabelog.form;

import java.sql.Time;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreEditForm {
	@NotNull
    private Integer id;
	
	@NotBlank(message = "店舗名を入力してください。")
    private String name;
        
    private MultipartFile imageName;
    
    @NotBlank(message = "説明を入力してください。")
    private String description;   
    
    @NotNull(message = "最低価格を入力してください。")
    @Min(value = 1, message = "価格帯は1円以上に設定してください。")
    private Integer lowestPrice;
    
    @NotNull(message = "最高価格を入力してください。")
    @Min(value = 1, message = "価格帯は1円以上に設定してください。")
    private Integer highestPrice;
    
    @NotBlank(message = "郵便番号を入力してください。")
    private String postalCode;
    
    @NotBlank(message = "住所を入力してください。")
    private String address;
    
    @NotNull(message = "開店時間を入力してください。")
    private Time openingTime;
    
    @NotNull(message = "閉店時間を入力してください。")
    private Time closingTime;
    
    @NotBlank(message = "休業日を入力してください。")
    private String regularHolidays;
    
    @NotNull(message = "座席数を入力してください。")
    @Min(value = 1, message = "座席数は1人以上に設定してください。")
    private Integer seatingCapacity;
    
    @NotNull(message = "カテゴリを選択してください。")
    private Integer categoryId;
    
    public StoreEditForm(Integer id, String name, MultipartFile imageName, String description, Integer lowestPrice, Integer highestPrice,
    		String postalCode, String Address, Time openingTime, Time closingTime, String regularHolidays, Integer seatingCapacity,
    		Integer categoryId) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.description = description;
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
        this.postalCode = postalCode;
        this.address = Address;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.regularHolidays = regularHolidays;
        this.seatingCapacity = seatingCapacity;
        this.categoryId = categoryId;        
    }
}
