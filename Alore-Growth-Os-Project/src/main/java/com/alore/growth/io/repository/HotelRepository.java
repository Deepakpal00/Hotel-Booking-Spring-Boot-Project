package com.alore.growth.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.alore.growth.io.model.Hotel;

@Component
public interface HotelRepository extends JpaRepository <Hotel, Long> {
	
	Hotel findByHotelId(int id);
	List<Hotel> findByCityOrderByRatingDesc(String city);
    List<Hotel> findBySize(Integer size);


}

