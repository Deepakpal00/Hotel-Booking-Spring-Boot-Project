package com.alore.growth.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alore.growth.io.model.Booking;
import com.alore.growth.io.model.Hotel;

import java.time.LocalDate;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select b from Booking b where b.hotelRoom.size = :size")
    List<Booking> findBookingByRoomSize(@Param("size") int size);

    List<Booking> findByHotelRoomAndEndDateGreaterThanOrderByEndDateAsc(Hotel hotelRoom, LocalDate date);
}
