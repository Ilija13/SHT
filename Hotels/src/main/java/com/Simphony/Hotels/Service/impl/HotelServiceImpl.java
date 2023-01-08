package com.Simphony.Hotels.Service.impl;

import com.Simphony.Hotels.Model.Hotel;
import com.Simphony.Hotels.Repository.HotelRepository;
import com.Simphony.Hotels.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getHotels() throws Exception {
        List<Hotel> hotels = hotelRepository.findByOrderByNameAsc();
        if (hotels.isEmpty()) {
            throw new Exception("THERE ARE NO HOTELS AT THE MOMENT");
        }
        return hotels;
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return hotel;
    }

    @Override
    public Hotel findById(Long id) throws Exception {
        if (hotelRepository.findById(id).isPresent()) {
            return hotelRepository.findById(id).get();
        } else throw new Exception("THE HOTEL DOES NOT EXIST");
    }

    @Override
    public Hotel updateHotel(Long id, Hotel hotel) throws Exception {

        if (!hotelRepository.findById(id).isPresent()) {
            throw new Exception("THE HOTEL DOES NOT EXIST TO BE UPDATED");
        }
        Hotel hotelDb = hotelRepository.findById(id).get();

        if (Objects.nonNull(hotel.getName()) && !"".equalsIgnoreCase(hotel.getName())) {
            hotelDb.setName(hotel.getName());
        }
        if (Objects.nonNull(hotel.getAddress()) && !"".equalsIgnoreCase(hotel.getAddress())) {
            hotelDb.setAddress(hotel.getAddress());
        }
        if (Objects.nonNull(hotel.getDescription()) && !"".equalsIgnoreCase(hotel.getDescription())) {
            hotelDb.setDescription(hotel.getDescription());
        }
        if (Objects.nonNull(hotel.getRating()) && !"".equalsIgnoreCase(hotel.getName())) {
            hotelDb.setRating(hotel.getRating());
        }
        return hotelRepository.save(hotelDb);
    }

    @Override
    public List<Hotel> findHotelByName(String hotelName) throws Exception {
        if (hotelRepository.findHotelByNameIgnoreCaseContaining(hotelName).isEmpty()) {
            throw new Exception("THERE IS NO HOTEL WITH SUCH NAME");
        }
        return hotelRepository.findHotelByNameIgnoreCaseContaining(hotelName);
    }

    @Override
    public List<Hotel> findHotelByAddress(String address) throws Exception {
        if (hotelRepository.findHotelByAddressIgnoreCaseContaining(address).isEmpty()) {
            throw new Exception("THERE ARE NO HOTELS ON THAT ADDRESS");
        }
        return hotelRepository.findHotelByAddressIgnoreCaseContaining(address);
    }

    @Override
    public String delete(Long id) throws Exception {
        if (hotelRepository.findById(id).isPresent()) {
            hotelRepository.deleteById(id);
            return "THE HOTEL WAS DELETED";
        } else throw new Exception("NO SUCH HOTEL TO BE DELETED");
    }

}
