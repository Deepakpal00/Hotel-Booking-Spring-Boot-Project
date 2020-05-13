package com.alore.growth.io.service;


import java.time.LocalDate;
import java.util.List;

import com.alore.growth.io.exception.NoAvailableRoomsException;
import com.alore.growth.io.model.Booking;
import com.alore.growth.io.model.Hotel;
import com.alore.growth.io.model.User;


public interface BookingService {

    Booking bookHotelRoom(User user ,int roomSize, LocalDate beginDate, LocalDate endDate) throws NoAvailableRoomsException;

    Hotel getHotelRoomForBooking(int roomSize, LocalDate beginDate, LocalDate endDate) throws NoAvailableRoomsException;

    List<Booking> getAllBookings();

    void cancelBooking(Long bookingId);
}
