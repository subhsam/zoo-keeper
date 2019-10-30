package com.myproject.zookeeper.services;

import java.sql.SQLException;
import java.util.List;

import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.Room;

public interface MyReportServices {
	
	List<Animal> animalsNotinRoom(String sortBy, String sortOrder) throws SQLException;
	
	List<Animal> animalsInSpecificRoom(int roomId,String sortBy, String sortOrder) throws SQLException;
	
	List<Room> favouriteRoomsForAnimal(int animalId) throws SQLException;

}
