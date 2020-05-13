package com.alore.growth.io.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int hotelId;
	private String name;
	private String address;
	private int rating;
	private String city;
    private int roomNumber;
	
	  @JsonBackReference
	  @ManyToOne private User manager;
	  
	  @Column(name = "size", nullable = false)
	    private Integer size;
	  
	  
	  public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}


	@JsonBackReference
	  @OneToMany(fetch = FetchType.EAGER, mappedBy="hotel", orphanRemoval = true)
	  @MapKeyColumn(name="id") private Map<Long, Room> rooms = new HashMap<Long,
	  Room>();
	  
	  
	  
	  @JsonManagedReference
	  @OneToMany(fetch = FetchType.EAGER, mappedBy="hotel", orphanRemoval = true)
	  @MapKeyColumn(name="id") private Map<String, String> comments = new HashMap<String, String>();
	 

	public Hotel() {
	}

	public Hotel(String name, String address, int rating, boolean status) {
		this.name = name;
		this.address = address;
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	
	  public Map<String,String> getComments() { return comments; }
	 
	public long getId() {
		return id;
	}

    public User getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}

	public int getRating() {
		return rating;
	}

	
	  public Map<Long, Room> getRooms() { return rooms; }
	 

	public void setAddress(String address) {
		this.address = address;
	}

	
	 public void setComments(Map<String,String>comments) { 
		 this.comments = comments; 
		 }
	

	public void setId(long id) {
		this.id = id;
	}

	
	  public void setManager(User manager) { this.manager = manager; }
	 

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
	  public void setRooms(Map<Long, Room> rooms) { this.rooms = rooms; }
	 

	@Override
	public String toString() {
		return "Id: " + getId() + "\nName: " + getName() + "\nAddress: " + getAddress() + "\nRating: " + getRating();
																																																												// "
																													
	}
}
