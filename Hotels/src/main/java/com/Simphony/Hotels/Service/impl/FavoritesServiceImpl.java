package com.Simphony.Hotels.Service.impl;

import com.Simphony.Hotels.Model.Favorites;
import com.Simphony.Hotels.Model.Hotel;
import com.Simphony.Hotels.Model.UserEntity;
import com.Simphony.Hotels.Repository.FavoritesRepository;
import com.Simphony.Hotels.Repository.HotelRepository;
import com.Simphony.Hotels.Service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public void createFavorite(Long hotelId) throws Exception {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (favoritesRepository.existsByHotelId(hotelId) && favoritesRepository.existsByUserId(user.getId())) {
            throw new Exception("THE HOTEL IS ALREADY IN YOUR FAVORITES");
        }
        Favorites favorite = new Favorites();
        favorite.setHotel(hotelRepository.findById(hotelId).get());
        favorite.setUser(user);
        favoritesRepository.save(favorite);
    }

    @Override
    public List<Hotel> getFavorites() throws Exception {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (favoritesRepository.findFavoritesByUserId(user.getId()).isEmpty()) {
            throw new Exception("YOU GOT NO FAVORITES");
        }
        return favoritesRepository.findFavoritesByUserId(user.getId());
    }

    @Override
    public void deleteFavorite(Long id) throws Exception {

        if (favoritesRepository.deleteByHotelId(id).isEmpty()) {
            throw new Exception("THE HOTEL IS NOT IN YOUR FAVORITES TO BE REMOVED");
        }
        favoritesRepository.deleteByHotelId(id);
    }


}
