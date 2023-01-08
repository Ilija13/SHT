package com.Simphony.Hotels.Controller;

import com.Simphony.Hotels.Model.Hotel;
import com.Simphony.Hotels.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/user/hotel/get")
    public List<Hotel> getHotels() throws Exception {
        return hotelService.getHotels();
    }

    @DeleteMapping("/admin/hotel/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(hotelService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/admin/hotel/create")
    public ResponseEntity<Hotel> postHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.addHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/user/hotel/get-id/{id}")
    public ResponseEntity<Hotel> viewHotel(@PathVariable("id") Long id) throws Exception {
        //return new ResponseEntity<>(hotelService.findById(id), HttpStatus.FOUND);
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @PutMapping("/admin/hotel/update/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long id,
                                             @RequestBody Hotel hotel)
            throws Exception {
        return new ResponseEntity<>(hotelService.updateHotel(id, hotel), HttpStatus.OK);
    }

    @GetMapping("/user/hotel/get-name/{name}")
    public List<Hotel> findHotel(@PathVariable("name") String hotelName) throws Exception {
        return hotelService.findHotelByName(hotelName);
    }

    @GetMapping("/user/hotel/get-address/{address}")
    public List<Hotel> hotelsName(@PathVariable("address") String address) throws Exception {
        return hotelService.findHotelByAddress(address);
    }
}
