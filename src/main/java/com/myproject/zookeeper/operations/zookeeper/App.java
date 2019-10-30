package com.myproject.zookeeper.operations.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.zookeeper.controllers.MyController;
import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.Room;
import com.myproject.zookeeper.operations.CrudOperations;
import com.myproject.zookeeper.operations.ReportOperations;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public final static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
    	testLog4j();
        
        //testAnimalCreation();
       //testAssignFavouriteRoomtoAnimal();
       // testremoveFavouriteRoomForAnimal();
        
       // testDisplayAnimals();
       // testfindRoomById();
      // testanimalsNotinRoom();
       // testMoveAnimalToRoom();
      // testDisplayAnimals();
       // testanimalsInSpecificRoom();
        testfavouriteRoomsForAnimal();
        
       // logger.info("result of delete:"+ CrudOperations.deleteAnimalRecord(1));
    }
    
    private static void testLog4j() {
    	
    	
    	PropertyConfigurator.configure("/resources/log4j.properties");
    	System.out.println("configured");
    }
    
    private static void testDisplayAnimals() {
    	
    	
		/*
		 * List<Animal> viewAnimals = CrudOperations.displayAnimals(); if(viewAnimals !=
		 * null & viewAnimals.size() > 0) { for(Animal animalObj : viewAnimals) {
		 * System.out.println(animalObj.toString()); } }
		 */
        
        MyController mycontObj = new MyController();
        //String animalList = mycontObj.getAllValues();
        //System.out.println("List of animals: "+animalList);
    }
    
    
    private static void testAnimalCreation() {
    	
    	
    	try {
    		
    		String mm = "{\"animalId\" : \"2\"}";
    		//Animal animalObject = new ObjectMapper().readValue(mm, Animal.class);
    		
    		Animal animalObject = new ObjectMapper().readValue(mm, Animal.class);
    		System.out.println("animalObject:"+animalObject.toString());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
private static void testAssignFavouriteRoomtoAnimal() {
    	
    	
    	Animal animalObj = CrudOperations.findAnimalById(7);
		System.out.println("animalObj:"+animalObj.toString());
		String animalObject = "{\"animalId\":\"7\",\"dateOfEntry\":\"2017-10-21\"}";
		System.out.println("animalObject::"+animalObject);
		MyController controller = new MyController();
		//String result = controller.assignFavouriteRoomtoAnimal("3",animalObject);
		//System.out.println("result:"+result);
    }



	private static void testremoveFavouriteRoomForAnimal() {
	
		
		Animal animalObj = CrudOperations.findAnimalById(7);
		boolean result = CrudOperations.removeFavouriteRoomForAnimal(3,animalObj);
		System.out.println("result:"+result);
	}
	
	private static void testfindRoomById() {
		
		
		Room roomObject = CrudOperations.findRoomById(1);
		System.out.println("roomObject:"+roomObject.toString());
	}
    
	
	private static void testanimalsNotinRoom() {
		String sortBy = "SORT_BY_TITLE";
		String sortOrder = "DESC";
		
		List<Animal> animalsNotinRoom = ReportOperations.animalsNotinRoom(sortBy, sortOrder);
		if(animalsNotinRoom != null & animalsNotinRoom.size() > 0) {
			for(Animal animalObj : animalsNotinRoom) {
				System.out.println(animalObj.toString()); 
				}
			}
	}
	
	private static void testMoveAnimalToRoom() {
		Room roomObject = new Room();
		roomObject.setRoomId(2);
		roomObject.setRoomName("Room1");
		System.out.println("result:"+CrudOperations.moveAnimalToRoom(7, roomObject));
		
	} 
	
	
	private static void testanimalsInSpecificRoom() {
		String sortBy = "SORT_BY_LOCATED";
		String sortOrder = "DESC";
		
		List<Animal> animalsInSpecificRoom = ReportOperations.animalsInSpecificRoom(1,sortBy,sortOrder);
		
		if(animalsInSpecificRoom != null & animalsInSpecificRoom.size() > 0) { for(Animal animalObj : animalsInSpecificRoom) {
     		 System.out.println(animalObj.toString()); } }
	}
	
	
	
	
	private static void testfavouriteRoomsForAnimal() {
		
		
		/*
		 * List<Room> favouriteRoomsForAnimal =
		 * ReportOperations.favouriteRoomsForAnimal(6);
		 * 
		 * 
		 * System.out.println("Size:"+favouriteRoomsForAnimal.size());
		 * 
		 * if(favouriteRoomsForAnimal != null & favouriteRoomsForAnimal.size() > 0) {
		 * for(Room roomObj : favouriteRoomsForAnimal) {
		 * System.out.println(roomObj.getRoomName()); } }
		 */
		  
		  MyController controller = new MyController();
			//String result = controller.favouriteRoomsForAnimal("6");
			//System.out.println("result:"+result);
	}
	
	
}



