package com.Simphony.Hotels.Controller;

import com.Simphony.Hotels.HotelsApplication;
import com.Simphony.Hotels.Model.Hotel;
import com.Simphony.Hotels.Service.FavoritesService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class FavoritesController {


    private FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @PostMapping("favorites/new/{hotelId}")
    public ResponseEntity<String> addToFavorites(@PathVariable("hotelId") Long hotelId) throws Exception {
        favoritesService.createFavorite(hotelId);
        return ResponseEntity.ok().build();
    }

    @JsonView(HotelsApplication.View.FavoritesBack.class)
    @GetMapping("favorites/all")
    public List<Hotel> getFavorites() throws Exception {
        return favoritesService.getFavorites();
    }

    @DeleteMapping("favorites/delete/{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable("id") Long id) throws Exception {
        favoritesService.deleteFavorite(id);
        return ResponseEntity.ok().build();
    }

}
