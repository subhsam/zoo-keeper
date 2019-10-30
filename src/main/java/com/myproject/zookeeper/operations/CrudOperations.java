package com.myproject.zookeeper.operations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.Room;

public class CrudOperations {
	
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
 
    public final static Logger logger = Logger.getLogger(CrudOperations.class);
 
    // This Method Is Used To Create The Hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }
    
    
    // This Method Used To Create A New Animal Record In The animal Table
    public static boolean createAnimal(Animal animalObj) {
    	boolean updateFlag = false;
        try {
        	// Getting Session Object From SessionFactory
        	sessionObj = buildSessionFactory().openSession();
        	// Getting Transaction Object From Session Object
        	sessionObj.beginTransaction();
        	Date today = new Date();
        	animalObj.setDateOfEntry(new java.sql.Date (today.getTime())); 
    		sessionObj.save(animalObj);

        	// Committing The Transactions To The Database
        	sessionObj.getTransaction().commit();
        	logger.info("\nSuccessfully Created new animal xzecords In The Database!\n");
        	updateFlag = true;
        } catch(Exception sqlException) {
        	if(null != sessionObj) {
        		if(null != sessionObj.getTransaction()) {
                    logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                    sessionObj.getTransaction().rollback();
                }
        		
        	}
            
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        
        return updateFlag;
    }
    
    // This Method Is Used To Display The Records From animal Table
    @SuppressWarnings("unchecked")
    public static List<Animal> displayAnimals() {
        List<Animal> animalList = new ArrayList<Animal>();        
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            animalList = sessionObj.createQuery("FROM Animal").list();
        } catch(Exception sqlException) {
        	if(null != sessionObj) {
        		if(null != sessionObj.getTransaction()) {
                    logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                    sessionObj.getTransaction().rollback();
                }
        		
        	}
            
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return animalList;
    }
    
    // This Method Is Used To Update An animal record In The animal Table   
    public static boolean updateAnimal(int animalId, Animal animalObjUpdate) {       
    	boolean updateFlag = false;
    	try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Animal animalObj = (Animal) sessionObj.get(Animal.class, animalId);
            animalObj.setAnimalName(animalObjUpdate.getAnimalName());
            animalObj.setDateOfEntry(animalObjUpdate.getDateOfEntry());
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nAnimal With Id?= " + animalId + " Is Successfully Updated In The Database!\n");
            updateFlag = true;
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    	return updateFlag;
    }
    
    
    //This Method Is Used To Delete A Particular animal record From The Database Table
    public static boolean deleteAnimalRecord(Integer animalId) {
    	boolean updateFlag = false;
    	try {
        	
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Animal animalObj = findAnimalById(animalId);
            sessionObj.delete(animalObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nAnimal With Id?= " + animalObj + " Is Successfully Deleted From The Database!\n");
            updateFlag = true;
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    	return updateFlag;
    }
    
    // This Method To Find Particular animal record In The Database Table
    public static Animal findAnimalById(int find_animalId) {
    	Animal findAnimalObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findAnimalObj = (Animal) sessionObj.get(Animal.class, find_animalId);
            //sessionObj.createQuery("FROM Animal where animalId= "+find_animalId).lo
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findAnimalObj;
    }
    
 
    
 // Method 1: This Method Used To Create A New Animal Record In The Database Table
    public static boolean createRoom(Room roomObj) {
    	boolean updateFlag = false;
        try {
        	// Getting Session Object From SessionFactory
        	sessionObj = buildSessionFactory().openSession();
        	// Getting Transaction Object From Session Object
        	sessionObj.beginTransaction();
        	Date today = new Date();
        	roomObj.setDateOfCreation(today); 
    		sessionObj.save(roomObj);

        	// Committing The Transactions To The Database
        	sessionObj.getTransaction().commit();
        	logger.info("\nSuccessfully Created Records In The Database!\n");
        	 updateFlag = true;
        } catch(Exception sqlException) {
        	if(null != sessionObj) {
        		if(null != sessionObj.getTransaction()) {
                    logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                    sessionObj.getTransaction().rollback();
                }
        		
        	}
            
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        
        return updateFlag;
    }
    
 // Method 3: This Method Is Used To Update A Record In The Database Table   
    public static boolean updateRoom(int roomId, Room roomObjUpdate) {       
    	boolean updateFlag = false;
    	try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            // Creating Transaction Entity
            Room roomObj = (Room) sessionObj.get(Room.class, roomId);
            if(roomObjUpdate.getRoomName()!=null) {
            	 roomObj.setRoomName(roomObjUpdate.getRoomName());
            }
            if(roomObjUpdate.getDateOfCreation()!=null) {
            	roomObj.setDateOfCreation(roomObjUpdate.getDateOfCreation());
            }
            if(roomObjUpdate.getAnimalsinRoom()!=null) {
            	roomObj.setAnimalsinRoom(roomObjUpdate.getAnimalsinRoom());;
            }
           
            
            
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nRoom With Id?= " + roomId + " Is Successfully Updated In The Database!\n");
            updateFlag = true;
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    	
    	return  updateFlag;
    }
    
    // This Method Is Used To Delete A Particular animal record From The Database Table
    public static boolean deleteRoomRecord(Integer roomId) {
        boolean updateFlag = false;
    	try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Room roomObj = findRoomById(roomId);
            sessionObj.delete(roomObj);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nRoom With Id?= " + roomId + " Is Successfully Deleted From The Database!\n");
            updateFlag = true;
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return  updateFlag;
    }  
    
    // This Method To Find Particular Room record In The Database Table
    public static Room findRoomById(Integer find_roomId) {
    	Room findRoomObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findRoomObj = (Room) sessionObj.get(Room.class, find_roomId);
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findRoomObj;
    }
    
    //This Method To assign a favorite room to an animal
    public static boolean assignFavouriteRoomtoAnimal(int find_roomId, Animal animalObject) {
    	boolean updateResult = false;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            Room selectedRoom = CrudOperations.findRoomById(find_roomId);
            selectedRoom.setFavoriteRoomsForAnimal(animalObject);
            
            sessionObj.save(selectedRoom);
        	updateResult = true;
            
         // Committing The Transactions To The Database
        	sessionObj.getTransaction().commit();
            //updateResult = true;
            
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return updateResult;
    }
    
  //This Method To remove a favorite room for an animal
    public static boolean removeFavouriteRoomForAnimal(int roomId,Animal animalObject) {
    	boolean updateResult = false;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            Room selectedRoom = CrudOperations.findRoomById(roomId);
            Set<Room> favoriteRooms = animalObject.getFavRooms();
            
            
            
            Room selectedFavRoom = null;
            for(Room favoriteRoom: favoriteRooms) {
            	
            	if(favoriteRoom.getRoomId()==roomId) {
            		selectedFavRoom = favoriteRoom; 
            		
            	}
            } 
            if(selectedFavRoom!=null) {
            	selectedRoom.setFavoriteRoomsForAnimal(null);
            	sessionObj.save(selectedRoom);
            }
            
         // Committing The Transactions To The Database
        	sessionObj.getTransaction().commit();
            updateResult = true;
            
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return updateResult;
    }
    
    
  //This Method To move an animal to a room
    public static boolean moveAnimalToRoom(int find_animalId, Room roomObject) {
    	boolean updateResult = false;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            Room roomObj = findRoomById(roomObject.getRoomId());
            Query query = sessionObj.createQuery("update Animal set assignedRoom = :roomId" +
    				" where animalId = :animalCode");
            query.setParameter("roomId", roomObj);
            query.setParameter("animalCode", find_animalId);
            query.executeUpdate();
            updateResult = true;
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return updateResult;
    }
    
    
  //This Method To remove a favorite room for an animal
    public static boolean removeAnimalFromRoom(int find_animalId, Room roomObject) {
    	boolean updateResult = false;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            Animal selectedAnimal = CrudOperations.findAnimalById(find_animalId);
            selectedAnimal.setAssignedRoom(null);
            sessionObj.save(selectedAnimal);
         // Committing The Transactions To The Database
        	sessionObj.getTransaction().commit();
            updateResult = true;
            
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return updateResult;
    }

}
