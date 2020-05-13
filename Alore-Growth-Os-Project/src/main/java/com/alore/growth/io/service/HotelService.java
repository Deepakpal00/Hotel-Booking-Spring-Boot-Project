package com.alore.growth.io.service;


import java.util.List;

import com.alore.growth.io.model.Hotel;

public interface HotelService {

    List<Hotel> getHotelRoomBySize(int size);
    Hotel createNewHotel(Hotel hotel);
}
