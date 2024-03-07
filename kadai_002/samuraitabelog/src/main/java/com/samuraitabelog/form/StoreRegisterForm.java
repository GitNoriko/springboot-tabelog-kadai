package com.samuraitabelog.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreRegisterForm {
	@NotBlank(message = "店舗名を入力してください。")
    private String name;
        
    private MultipartFile imageFile;
    
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
    private String openingTime;
    
    @NotNull(message = "閉店時間を入力してください。")
    private String closingTime;
    
    @NotBlank(message = "定休日を入力してください。")
    private String regularHolidays;
    
    @NotNull(message = "座席数を入力してください。")
    @Min(value = 1, message = "座席数は1人以上に設定してください。")
    private Integer seatingCapacity;
    
    @NotNull(message = "カテゴリを選択してください。")
    private Integer categoryId;
}
