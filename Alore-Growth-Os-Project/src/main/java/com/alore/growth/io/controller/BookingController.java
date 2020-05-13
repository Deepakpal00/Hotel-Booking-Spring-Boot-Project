package com.alore.growth.io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.alore.growth.io.exception.NoAvailableRoomsException;
import com.alore.growth.io.model.Booking;
import com.alore.growth.io.model.User;
import com.alore.growth.io.service.BookingService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // book a room // It will required current user login and Room size and start date And end date
    @GetMapping("/bookRoom")
    @ResponseBody
    public Booking bookHotelRoom(@RequestBody User user, @RequestParam(value = "roomSize") int roomSize, 
    		@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, 
    		@RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws NoAvailableRoomsException {
    	return bookingService.bookHotelRoom(user,roomSize, startDate, endDate);
         
    }

    // for cancel booking
    @DeleteMapping("/cancelBooking")
    @ResponseBody
    public String cancelBooking(@RequestParam(value = "id") Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return "OK";
    }

// for get all booking
    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
