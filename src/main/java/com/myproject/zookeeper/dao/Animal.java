package com.myproject.zookeeper.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="animal")
public class Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="animal_id")
    private int animalId;
 
    @Column(name="animal_name")
    private String animalName;
 
    
    @Column(name="date_of_entry")
    private Date dateOfEntry;
    
    
    
    
    
    @ManyToOne
    @JoinColumn(name="room_id_assigned")
    private Room assignedRoom;
    
    @OneToMany(mappedBy="favoriteRoomsForAnimal", cascade = CascadeType.ALL)
    Set<Room> favRooms;
    
    @Override
    public String toString() {
    	String roomID=null;
    	if(this.assignedRoom!=null) {
    		roomID = String.valueOf(this.assignedRoom);
    	}
    		
    		
    	
        return "Animal Details?= Id: " + this.animalId + ", animalName: " + this.animalName + ", Date of addition.: " + this.dateOfEntry.toString()+
        		", Room assigned: "+roomID;
        
        		
    }
    

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	
	

	public Date getDateOfEntry() {
		return dateOfEntry;
	}


	public void setDateOfEntry(Date dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Room getAssignedRoom() {
		return assignedRoom;
	}


	public void setAssignedRoom(Room assignedRoom) {
		this.assignedRoom = assignedRoom;
	}


	public Set<Room> getFavRooms() {
		return favRooms;
	}


	public void setFavRooms(Set<Room> favRooms) {
		this.favRooms = favRooms;
	}


    
    
    
	

}
