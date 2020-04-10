package com.samplewebapp.places;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Place {
	private int id;
	
	@Size(min = 2, message = "At lest 2 characters")
	private String name;
	
	private String user;
	
	@Size(min = 2, message = "At least 2 characters")
	private String description;
	
	private String imageName;
	private boolean beenThere;
	
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date targetDate;
	
	public Place() {}
	
	public Place(int id, String name, String user, String description,
			String imageName, boolean beenThere, Date targetDate) {
		this.id = id;
		this.name = name;
		this.user = user;
		this.description = description;
		this.imageName = imageName;
		this.beenThere = beenThere;
		this.targetDate = targetDate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public boolean isBeenThere() {
		return beenThere;
	}
	
	public void setBeenThere(boolean beenThere) {
		this.beenThere = beenThere;
	}
	
	public Date getTargetDate() {
		return targetDate;
	}
	
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
	@Override
	public String toString() {
		return String.format("Place [id=%s, name=%s, user=%s, description=%s, imageName=%s, beenThere=%s, targetDate=%s]",
				id, name, user, description, imageName, beenThere, targetDate);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
