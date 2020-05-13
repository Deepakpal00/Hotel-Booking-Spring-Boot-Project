package com.alore.growth.io.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "booking")
public class Booking {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    private LocalDate beginDate;

    private LocalDate endDate;
	
	@ManyToOne
	private User user;
	
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Room> rooms = new HashSet<Room>();
	
	public Booking(){}
	
	public Booking(long id, LocalDate begin_date, LocalDate end_date, User user){
		this.id = id;
		this.beginDate = begin_date;
		this.endDate = end_date;
		this.user = user;
	}
	
	 @ManyToOne
	    @JoinColumn(name = "hotel_room_id")
	    private Hotel hotelRoom;
	
	public Hotel getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(Hotel hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	
	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
