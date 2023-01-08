package com.Simphony.Hotels.Service;

import com.Simphony.Hotels.Model.Hotel;

import java.util.List;

public interface FavoritesService {
    void createFavorite(Long hotelId) throws Exception;

    List<Hotel> getFavorites() throws Exception;

    void deleteFavorite(Long id) throws Exception;
}
