package com.myproject.zookeeper.services;

import java.sql.SQLException;
import java.util.List;

import com.myproject.zookeeper.dao.*;

public interface MyCrudServices {
	
	List<Animal> listAllAnimals() throws SQLException;
	
	boolean addAnimal(Animal animalObj) throws SQLException;
	
	boolean deleteAnimal(Animal animalObj) throws SQLException;
	
	boolean updateAnimal(Animal animalObj) throws SQLException;
	
	boolean addRoom(Room roomObj) throws SQLException;
	
	boolean deleteRoom(Room roomObj) throws SQLException;
	
	boolean updateRoom(Room roomObj) throws SQLException;
	
	Room findRoomById(Room roomObj) throws SQLException;
	
	boolean assignFavouriteRoomtoAnimal(int find_roomId, Animal animalObject) throws SQLException;
	
	boolean removeFavouriteRoomForAnimal(int roomId,Animal animalObject) throws SQLException;
	
	boolean moveAnimalToRoom(int find_animalId, Room roomObject) throws SQLException;
	
	boolean removeAnimalFromRoom(int find_animalId, Room roomObject) throws SQLException;

}
