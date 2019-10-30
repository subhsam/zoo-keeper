package com.myproject.zookeeper.servicesimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.AnimalClone;
import com.myproject.zookeeper.dao.Room;
import com.myproject.zookeeper.operations.CrudOperations;
import com.myproject.zookeeper.services.MyCrudServices;

public class MyCrudServicesImpl implements MyCrudServices {
	
	private static class SingleTonHelper{
		
		private static final MyCrudServicesImpl INSTANCE = new MyCrudServicesImpl();
	}
	
	// This Method is used for getting an instance of the impl class
	public static MyCrudServicesImpl getInstance() {
		return SingleTonHelper.INSTANCE;
	}

	// This method is to get the list of all animals in the zoo
	public List<Animal> listAllAnimals() throws SQLException {
		
		List<Animal> viewAnimals = CrudOperations.displayAnimals();
		List<AnimalClone> animalCloneList = new ArrayList<AnimalClone>();
		
		for(Animal animal:viewAnimals) {
			
			AnimalClone animalCloneObj = new AnimalClone();
			animalCloneObj.setAnimalName(animal.getAnimalName());
			animalCloneObj.setDateOfEntry(animal.getDateOfEntry().toString());
			animalCloneList.add(animalCloneObj);
		}
		return viewAnimals;
	}
	
	// This Method Used To add a new Animal in the zoo
	public boolean addAnimal(Animal animalObj) throws SQLException {
		boolean result = false;
		result = CrudOperations.createAnimal(animalObj);
		return result;
	}
	
	// This Method Used To remove an Animal from the zoo
	public boolean deleteAnimal(Animal animalObj) throws SQLException {
		boolean result = false;
		result = CrudOperations.deleteAnimalRecord(animalObj.getAnimalId());
		return result;
	}

	// This Method Used To update an Animal record in the zoo
	public boolean updateAnimal(Animal animalObj) throws SQLException {
		boolean result = false;
		result = CrudOperations.updateAnimal(animalObj.getAnimalId(),animalObj);
		return result;
	}
	
	
	// This Method Used To add a new Room record in the zoo
	public boolean addRoom(Room roomObj) throws SQLException {
		boolean result = false;
		result = CrudOperations.createRoom(roomObj);
		return result;
	}

	// This Method Used To delete a Room record in the zoo
	public boolean deleteRoom(Room roomObj) throws SQLException {
		boolean result = false;
		result = CrudOperations.deleteRoomRecord(roomObj.getRoomId());
		return result;
	}
	
	// This Method Used To update a Room record in the zoo
	public boolean updateRoom(Room roomObj) throws SQLException {
		boolean result = false;
		result = CrudOperations.updateRoom(roomObj.getRoomId(),roomObj);
		return result;
	}
	
	// This Method Used To find a Room record in the zoo
	public Room findRoomById(Room roomObj) throws SQLException {
		Room selectedRoom = CrudOperations.findRoomById(roomObj.getRoomId());
		return selectedRoom;
	}
	
	// This Method Used To assign a favorite room to an animal
	public boolean assignFavouriteRoomtoAnimal(int find_roomId, Animal animalObject) throws SQLException {
		boolean result = false;
		result = CrudOperations.assignFavouriteRoomtoAnimal(find_roomId,animalObject);
		return result;
	}

	// This Method Used To remove a favorite room for an animal
	public boolean removeFavouriteRoomForAnimal(int roomId, Animal animalObject) throws SQLException {
		boolean result = false;
		result = CrudOperations.removeFavouriteRoomForAnimal(roomId, animalObject);
		return result;
	}
	
	// This Method Used To move an Animal to a room
	public boolean moveAnimalToRoom(int find_animalId, Room roomObject) throws SQLException {
		boolean result = false;
		result = CrudOperations.moveAnimalToRoom(find_animalId, roomObject);
		return result;
	}

	// This Method Used To remove an Animal from a room
	public boolean removeAnimalFromRoom(int find_animalId, Room roomObject) throws SQLException {
		boolean result = false;
		result = CrudOperations.removeAnimalFromRoom(find_animalId, roomObject);
		return result;
	}

}
