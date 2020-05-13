package com.alore.growth.io.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alore.growth.io.model.Hotel;
import com.alore.growth.io.repository.HotelRepository;
import com.alore.growth.io.service.HotelService;


import java.util.List;


@Service
public class HotelRoomServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRoomRepository;
   
	@Override
    public List<Hotel> getHotelRoomBySize(int size) {
        return hotelRoomRepository.findBySize(size);
    }

	@Override
	public Hotel createNewHotel(Hotel hotel) {
		
		return hotelRoomRepository.save(hotel);
	}
}
