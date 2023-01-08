package com.Simphony.Hotels.Service;

import com.Simphony.Hotels.Model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getHotels() throws Exception;

    Hotel addHotel(Hotel hotel);

    Hotel findById(Long id) throws Exception;

    Hotel updateHotel(Long id, Hotel hotel) throws Exception;

    List<Hotel> findHotelByName(String hotelName) throws Exception;

    List<Hotel> findHotelByAddress(String address) throws Exception;

    String delete(Long id) throws Exception;
}
