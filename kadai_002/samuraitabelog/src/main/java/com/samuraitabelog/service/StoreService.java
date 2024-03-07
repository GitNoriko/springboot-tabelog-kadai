package com.samuraitabelog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.samuraitabelog.entity.Category;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.form.StoreEditForm;
import com.samuraitabelog.form.StoreRegisterForm;
import com.samuraitabelog.repository.CategoryRepository;
import com.samuraitabelog.repository.StoreRepository;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
	
	public StoreService(StoreRepository storeRepository, CategoryRepository categoryRepository) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional
	public void create(StoreRegisterForm storeRegisterForm) {
		Store store = new Store();
		MultipartFile imageFile = storeRegisterForm.getImageFile();
		
		Integer categoryId = Integer.valueOf(storeRegisterForm.getCategoryId());
		Category category = categoryRepository.getReferenceById(categoryId);
		//Time openingTime = Time.valueOf(storeRegisterForm.getOpeningTime());
		//Time closingTime = Time.valueOf(storeRegisterForm.getClosingTime());
		// 開店時間と閉店時間の変換
		LocalTime localOpeningTime = LocalTime.parse(storeRegisterForm.getOpeningTime(), DateTimeFormatter.ofPattern("HH:mm"));
		LocalTime localClosingTime = LocalTime.parse(storeRegisterForm.getClosingTime(), DateTimeFormatter.ofPattern("HH:mm"));
		// LocalTimeからTimeに変換
		Time openingTime = Time.valueOf(localOpeningTime);
		Time closingTime = Time.valueOf(localClosingTime);
		        		
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
        }
        
        store.setName(storeRegisterForm.getName());                
        store.setDescription(storeRegisterForm.getDescription());
        store.setLowestPrice(storeRegisterForm.getLowestPrice());
        store.setHighestPrice(storeRegisterForm.getHighestPrice());
        store.setPostalCode(storeRegisterForm.getPostalCode());
        store.setAddress(storeRegisterForm.getAddress());
        store.setOpeningTime(openingTime);
        store.setClosingTime(closingTime);
        store.setRegularHolidays(storeRegisterForm.getRegularHolidays());        
        store.setSeatingCapacity(storeRegisterForm.getSeatingCapacity());
        store.setCategory(category);
                    
        storeRepository.save(store);
    }  
    
	@Transactional
    public void update(StoreEditForm storeEditForm) {
		Store store = storeRepository.getReferenceById(storeEditForm.getId());
		
		MultipartFile imageFile = storeEditForm.getImageName();
		
		Integer categoryId = Integer.valueOf(storeEditForm.getCategoryId());
		   Category category = categoryRepository.getReferenceById(categoryId);
		//Time openingTime = Time.valueOf(shopRegisterForm.getOpeningTime());
		//Time closingTime = Time.valueOf(shopRegisterForm.getClosingTime());
		// 開店時間と閉店時間の変換
		LocalTime localOpeningTime = LocalTime.parse(storeEditForm.getOpeningTime().toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
		LocalTime localClosingTime = LocalTime.parse(storeEditForm.getClosingTime().toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
		// LocalTimeからTimeに変換
		Time openingTime = Time.valueOf(localOpeningTime);
		Time closingTime = Time.valueOf(localClosingTime);
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
       }
        
        store.setName(storeEditForm.getName());
        store.setDescription(storeEditForm.getDescription());
        store.setLowestPrice(storeEditForm.getLowestPrice());
        store.setHighestPrice(storeEditForm.getHighestPrice());
        store.setPostalCode(storeEditForm.getPostalCode());
        store.setAddress(storeEditForm.getAddress());
        store.setOpeningTime(openingTime);
        store.setClosingTime(closingTime);
        store.setRegularHolidays(storeEditForm.getRegularHolidays());        
        store.setSeatingCapacity(storeEditForm.getSeatingCapacity());
        store.setCategory(category);
        
        storeRepository.save(store);
	}
	
        public String generateNewFileName(String fileName) {
            String[] fileNames = fileName.split("\\.");                
            for (int i = 0; i < fileNames.length - 1; i++) {
                fileNames[i] = UUID.randomUUID().toString();            
            }
            String hashedFileName = String.join(".", fileNames);
            return hashedFileName;
        }
        
        public void copyImageFile(MultipartFile imageFile, Path filePath) {           
            try {
                Files.copy(imageFile.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
	}
}