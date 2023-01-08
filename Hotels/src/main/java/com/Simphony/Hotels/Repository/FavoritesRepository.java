package com.Simphony.Hotels.Repository;

import com.Simphony.Hotels.Model.Favorites;
import com.Simphony.Hotels.Model.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {


    @Query("select f.hotel from Favorites f WHERE f.user.id =?1")
    List<Hotel> findFavoritesByUserId(Long id);

    @Transactional
    String deleteByHotelId(Long hotelId);

    boolean existsByHotelId(Long hotelId);

    boolean existsByUserId(Long userId);
}
