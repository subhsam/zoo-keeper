package com.myproject.zookeeper.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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


@Entity
@Table(name="room")
public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
    private int roomId;
 
    @Column(name="room_name")
    private String roomName;
 
    @Column(name="date_of_creation")
    private Date dateOfCreation;
    
    
    
    @ManyToOne
    @JoinColumn(name="animal_id_favourite")
    private Animal favoriteRoomsForAnimal;
    
    @OneToMany(mappedBy="assignedRoom", cascade = CascadeType.ALL)
    Set<Animal> animalsinRoom ;
    
    @Override
    public String toString() {
        return "Room Details?= Id: " + this.roomId + ", roomName: " + this.roomName + ", Date of creation.: " + this.dateOfCreation+", animalIdFavourite: ";
    }

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}



	public Set<Animal> getAnimalsinRoom() {
		return animalsinRoom;
	}

	public void setAnimalsinRoom(Set<Animal> animalsinRoom) {
		this.animalsinRoom = animalsinRoom;
	}

	public Animal getFavoriteRoomsForAnimal() {
		return favoriteRoomsForAnimal;
	}

	public void setFavoriteRoomsForAnimal(Animal favoriteRoomsForAnimal) {
		this.favoriteRoomsForAnimal = favoriteRoomsForAnimal;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
