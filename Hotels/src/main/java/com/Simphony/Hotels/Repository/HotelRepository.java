package com.Simphony.Hotels.Repository;

import com.Simphony.Hotels.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByOrderByNameAsc();

    List<Hotel> findHotelByNameIgnoreCaseContaining(String hotelName);

    List<Hotel> findHotelByAddressIgnoreCaseContaining(String address);

}
