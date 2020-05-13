package com.alore.growth.io.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alore.growth.io.exception.HotelNotFoundException;
import com.alore.growth.io.model.Comment;
import com.alore.growth.io.model.Hotel;
import com.alore.growth.io.model.User;
import com.alore.growth.io.repository.HotelRepository;
import com.alore.growth.io.service.HotelService;


@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	HotelRepository hotels;
	
	@Autowired
	HotelService hotelService;
	
	// create New Hotel
	@PostMapping("/create")  
	public @ResponseBody Hotel createHotel(@RequestBody Hotel hotel) {
		
		return hotelService.createNewHotel(hotel);
	  }
	
	// find particular hotel
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET) 	
	public @ResponseBody Hotel show(@PathVariable("id") int id) {
		
		 Hotel hotel = hotels.findByHotelId(id);
		 if(hotel == null)
			 throw new HotelNotFoundException();
		 return hotel;
		
	}
	
	// find by city. It will return result with high rating order
	@RequestMapping(value="/city/{city}", method=RequestMethod.GET) 	
	public @ResponseBody List<Hotel> show(@PathVariable("city") String cityId) {
		List<Hotel> hotel = hotels.findByCityOrderByRatingDesc(cityId);
		if(hotel == null) 
			throw new HotelNotFoundException();
	
		return hotel;
	}
	
	// for showing result according rating and comments
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET) 	
	public @ResponseBody   Map<String, String> showByRatingAndComments(@PathVariable("id") int hotelId) {
		
		 Hotel hotel = hotels.findByHotelId(hotelId);
		 if(hotel == null)
			 throw new HotelNotFoundException();
		 HashMap<Integer,Map<String,String>> hashmap = new HashMap<Integer,Map<String,String>>(); //
		  
		 return hashmap.put(hotel.getRating(), hotel.getComments());
		  
		
	}
	
	// for update a hotel
	@PostMapping("/update")  
	public @ResponseBody Hotel updateHotel(@RequestBody Hotel hotel) {
		
		return hotels.save(hotel);
	  }
	
	// for Adding reviews and comment for particular hotel
		@RequestMapping(value="/add/{id}/{comment}", method=RequestMethod.GET) 	
		public @ResponseBody  void addReviewAndComments(@RequestBody User user,@PathVariable("id") int hotelId, @PathVariable("comment") String comments) {
			
			 Hotel oldhotel = hotels.findByHotelId(hotelId);
			 if(oldhotel == null)
				 throw new HotelNotFoundException();
			 HashMap<String,String> map = new HashMap<String,String>();
			 map.put(user.getName(),comments);
			 oldhotel.setComments(map);
			 hotels.save(oldhotel);
			
		}

}
