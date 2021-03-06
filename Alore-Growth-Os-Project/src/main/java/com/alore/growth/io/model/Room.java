package com.alore.growth.io.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "room")
public class Room implements Comparable<Object>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int floor;	
	private String room_number;	
		
	@JsonManagedReference
	@ManyToOne
	private Hotel hotel;
	
	private int price;
	
	
	@JsonBackReference
	@ManyToMany(mappedBy="rooms")
	 private Set<Booking> bookings = new HashSet<Booking>();
	
	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Room() {}
	
	public Room (long id, int floor, String room_number, Hotel hotel, int price) {
		this.floor = floor;
		this.id = id;
		this.room_number = room_number;	
		this.hotel = hotel;
		this.setPrice(price);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getRoom_number() {
		return room_number;
	}

	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}

	



	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int compareTo(Object o) {		
		return getRoom_number().compareTo(((Room) o).getRoom_number());
	}
}