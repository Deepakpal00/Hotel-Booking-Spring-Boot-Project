package com.alore.growth.io.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alore.growth.io.model.Hotel;
import com.alore.growth.io.model.Room;
import com.alore.growth.io.repository.HotelRepository;
import com.alore.growth.io.repository.RoomRepository;



@RestController
@RequestMapping(value="/hotels")
public class RoomController {
	
	@Autowired
	HotelRepository hotels;
	
	@Autowired
    RoomRepository rooms;
	
	  // POST /hotels/{id}/rooms/ - creates a new room
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public String saveRoom(@PathVariable("id") int id) {  
    	Hotel hotel = hotels.findByHotelId(id);  
    	Room room = new Room();
    	room.setHotel(hotel);    	
    	rooms.save(room);
    	return "room created in hotal with hotal id "+id;
    }
    
    // GET  /hotels/{id}/rooms/ - show the list of rooms of the hotel
    @RequestMapping(value="{id}/rooms", method=RequestMethod.GET)
    public List<Room> showRooms(@PathVariable("id") int id) {
    	Hotel hotel = hotels.findByHotelId(id);
    	Map<Long, Room> hotel_rooms = hotel.getRooms();
    	Map<Integer, Room> rooms = new HashMap<Integer, Room>();
    	
    	for(Long entry : hotel_rooms.keySet()){
    		Room r = hotel_rooms.get(entry);
    		rooms.put(Integer.parseInt(r.getRoom_number()), r);
    	}
    	List<Room> orderedRooms = new ArrayList<Room>();
    	SortedSet<Integer> orderedSet = new TreeSet<Integer>(rooms.keySet());
    	for(Integer key : orderedSet)
    		orderedRooms.add(rooms.get(key));
    	
    	
    	return orderedRooms;
    }
    
    

}
