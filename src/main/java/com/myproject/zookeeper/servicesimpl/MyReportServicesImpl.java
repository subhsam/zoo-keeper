package com.myproject.zookeeper.servicesimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.Room;
import com.myproject.zookeeper.operations.ReportOperations;
import com.myproject.zookeeper.services.MyReportServices;

public class MyReportServicesImpl implements MyReportServices {
	
	private static class SingleTonHelper{
		
		private static final MyReportServicesImpl INSTANCE = new MyReportServicesImpl();
	}
	
	public static MyReportServicesImpl getInstance() {
		return SingleTonHelper.INSTANCE;
	}

	// This Method Used To get list of animals not in any room
	public List<Animal> animalsNotinRoom(String sortBy, String sortOrder) throws SQLException {
		List<Animal> animalsNotinRoom = new ArrayList<Animal>();
		animalsNotinRoom = ReportOperations.animalsNotinRoom(sortBy, sortOrder);
		return animalsNotinRoom;
	}
	
	// This Method Used To get a list of animals in a specific room
	public List<Animal> animalsInSpecificRoom(int roomId, String sortBy, String sortOrder) throws SQLException {
		List<Animal> animalsInSpecificRoom = new ArrayList<Animal>();
		animalsInSpecificRoom = ReportOperations.animalsInSpecificRoom(roomId, sortBy, sortOrder);
		return animalsInSpecificRoom;
	}
	
	// This Method Used To get a list of favorite rooms for an animal
	public List<Room> favouriteRoomsForAnimal(int animalId) throws SQLException {
		
		List<Room> favouriteRoomsForAnimal = new ArrayList<Room>();
		favouriteRoomsForAnimal =ReportOperations.favouriteRoomsForAnimal(animalId);
		return favouriteRoomsForAnimal;
	}

}
