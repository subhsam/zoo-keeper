package com.myproject.zookeeper.operations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.Room;
import com.myproject.zookeeper.servicesimpl.SortingHelper;

public class ReportOperations {
	
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
 
    public final static Logger logger = Logger.getLogger(ReportOperations.class);
 
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
    
 // This Method Is Used To Display Animals who are not in an room
    @SuppressWarnings("unchecked")
    public static List<Animal> animalsNotinRoom(String sortBy, String sortOrder) {
        List<Animal> animalsList = new ArrayList<Animal>();        
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            String sortString = "";
            if(sortBy.equalsIgnoreCase(SortingHelper.SORT_BY_TITLE)) {
            	sortString = " animalName "+ sortOrder;
            	
            }else if(sortBy.equalsIgnoreCase(SortingHelper.SORT_BY_LOCATED)) {
            	
            	sortString = " dateOfEntry "+sortOrder;
            	
            }
            Criteria cr = sessionObj.createCriteria(Animal.class);
            
            
            List<Object[]> collectionAnimals = sessionObj.createQuery(" select animalName, dateOfEntry FROM Animal where assignedRoom="+null+
            " order by "+sortString).list();
            
            
        	 for(Object[] animalObject: collectionAnimals)
        	 {
        		 
        		 Animal animal = new Animal();
        		 animal.setAnimalName((String)animalObject[0]);
        		 animal.setDateOfEntry((Date)animalObject[1]);
        		 animalsList.add(animal);      		 
        		
        	 }
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
        return animalsList;
    }
    
    
 // This Method Is Used To Display animals in a certain room
    @SuppressWarnings("unchecked")
    public static List<Animal> animalsInSpecificRoom(int roomId, String sortBy, String sortOrder) {
        List<Animal> animalsList = new ArrayList<Animal>();        
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            String sortString = "";
            if(sortBy.equalsIgnoreCase(SortingHelper.SORT_BY_TITLE)) {
            	sortString = " animalName "+ sortOrder;
            	
            }else if(sortBy.equalsIgnoreCase(SortingHelper.SORT_BY_LOCATED)) {
            	
            	sortString = " dateOfEntry "+sortOrder;
            	
            }
            Room roomObj = CrudOperations.findRoomById(roomId);
            
            Query query = sessionObj.createQuery("select animalName, dateOfEntry FROM Animal where assignedRoom=:roomObj order by :sortString");
            query.setParameter("roomObj", roomObj);
            query.setParameter("sortString", sortString);
            
            List<Object[]> collectionAnimals =query.list();
            
            for(Object[] animalObject: collectionAnimals)
       	 {
       		 
       		 Animal animal = new Animal();
       		 animal.setAnimalName((String)animalObject[0]);
       		 animal.setDateOfEntry((Date)animalObject[1]);
       		 animalsList.add(animal);      		 
       		
       	 }
            
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
        return animalsList;
    }
    
    
 // This Method Is Used To Display animals in a certain room
    @SuppressWarnings("unchecked")
    public static List<Room> favouriteRoomsForAnimal(int animalId) {
    	List<Room> roomList = new ArrayList<Room>();        
        try {
            
            
            Animal animalObj = CrudOperations.findAnimalById(animalId);
            
         // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            
            Query query = sessionObj.createQuery("select roomName FROM Room where favoriteRoomsForAnimal=:animalObj");
            
            query.setParameter("animalObj", animalObj);
            
            roomList =query.list();
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
        return roomList;
    }


}
