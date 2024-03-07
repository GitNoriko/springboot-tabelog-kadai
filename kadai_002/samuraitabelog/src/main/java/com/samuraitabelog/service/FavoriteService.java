package com.samuraitabelog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuraitabelog.entity.Favorite;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;
import com.samuraitabelog.repository.FavoriteRepository;

@Service
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;        
    
    public FavoriteService(FavoriteRepository favoriteRepository) {        
        this.favoriteRepository = favoriteRepository;        
    }
    
    @Transactional
    public void create(Store store, User user) {
        Favorite favorite = new Favorite();        
        
        favorite.setStore(store);                
        favorite.setUser(user);
                    
        favoriteRepository.save(favorite);
    }
    
    public boolean isFavorite(Store store, User user) {
        return favoriteRepository.findByStoreAndUser(store, user) != null;
    }
}
