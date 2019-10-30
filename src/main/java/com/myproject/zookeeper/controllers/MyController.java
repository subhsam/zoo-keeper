package com.myproject.zookeeper.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.myproject.zookeeper.dao.Animal;
import com.myproject.zookeeper.dao.AnimalClone;
import com.myproject.zookeeper.dao.Room;
import com.myproject.zookeeper.dao.RoomClone;
import com.myproject.zookeeper.servicesimpl.MyCrudServicesImpl;
import com.myproject.zookeeper.servicesimpl.MyReportServicesImpl;
import com.thoughtworks.xstream.mapper.ArrayMapper;

public class MyController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyCrudServicesImpl crudserviceImpl = MyCrudServicesImpl.getInstance();
	private MyReportServicesImpl reportserviceImpl = MyReportServicesImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set the response message's MIME type
	      response.setContentType("text/html;charset=UTF-8");
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	      
	      String action = request.getParameter("action");
	      String getReport = request.getParameter("getReport");
	      
	      PropertyConfigurator.configure("log4j.properties");
	 
	      // Write the response message, in an HTML page
	      try {
	         out.println("<!DOCTYPE html>");
	         out.println("<html><head>");
	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	         out.println("<title>Zoo keeper</title></head>");
	         out.println("<body>");
	         out.println("<h1>Zoo keeper</h1>"); 
	         
	         // check for all the CRUD operations
	         if(null !=action) {
	        	 if("getAllValues".equals(action)) {
	        		 
	        		 out.println("<p>Animals"+getAllValues()+"</p>"); 
	        		 
	        	 }else if("addAnimal".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>Add animal result: "+addAnimal(requestBody)+"</p>"); 
	        		 
	        	 } else if("deleteAnimal".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>Delete animal result: "+deleteAnimal(requestBody)+"</p>"); 
	        		 
	        	 } else if("updateAnimal".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>Update animal result: "+updateAnimal(requestBody)+"</p>"); 
	        		 
	        	 } else if("addRoom".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>addRoom result: "+addRoom(requestBody)+"</p>"); 

	        	 } else if("readRoom".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>readRoom result: "+readRoom(requestBody)+"</p>"); 

	        	 } else if("updateRoom".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>updateRoom result: "+updateRoom(requestBody)+"</p>"); 
	        	 } else if("deleteRoom".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 out.println("<p>deleteRoom result: "+deleteRoom(requestBody)+"</p>"); 
	        	 } else if("assignFavouriteRoomtoAnimal".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 String roomId = request.getParameter("roomId") ; 
	        		 out.println("<p>assignFavouriteRoomtoAnimal result: "+assignFavouriteRoomtoAnimal(roomId, requestBody)+"</p>"); 
	        	 } else if("removeFavouriteRoomForAnimal".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 String roomId = request.getParameter("roomId") ;
	        		 out.println("<p>removeFavouriteRoomForAnimal result: "+removeFavouriteRoomForAnimal(roomId, requestBody)+"</p>"); 
	        	 } else if("moveAnimalToRoom".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 String animalId = request.getParameter("animalId") ;
	        		 out.println("<p>moveAnimalToRoom result: "+moveAnimalToRoom(animalId,requestBody)+"</p>"); 
	        	 } else if("removeAnimalFromRoom".equals(action)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 String animalId = request.getParameter("animalId") ;
	        		 out.println("<p>removeAnimalFromRoom result: "+removeAnimalFromRoom(animalId,requestBody)+"</p>"); 
	        	 }
	        	 
	        	 
	         } 
	         // check for all the REPORT operations
	         else if(null!=getReport){
	        	 
	        	 if("animalsNotinRoom".equals(getReport)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 JsonNode jNode = new ObjectMapper().readTree(requestBody);
	        		 String sortBy = jNode.get("sortBy").asText();
	        		 String sortOrder = jNode.get("sortOrder").asText();
	        		 out.println("<p>Animals not in Room "+animalsNotinRoom(sortBy, sortOrder)+"</p>");
	        		 
	        	 } else if ("animalsInSpecificRoom".equals(getReport)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 JsonNode jNode = new ObjectMapper().readTree(requestBody);
	        		 String roomId = jNode.get("roomId").asText();
	        		 String sortBy = jNode.get("sortBy").asText();
	        		 String sortOrder = jNode.get("sortOrder").asText();
	        		 out.println("<p>Animals in Room "+animalsInSpecificRoom(roomId, sortBy, sortOrder )+"</p>"); 
	        		 
	        	 } else if ("favouriteRoomsForAnimal".equals(getReport)) {
	        		 String requestBody = request.getReader().lines().collect(Collectors.joining());
	        		 JsonNode jNode = new ObjectMapper().readTree(requestBody);
	        		 String animalId = jNode.get("animalId").asText();
	        		 out.println("<p>Favourite room for animal "+favouriteRoomsForAnimal(animalId)+"</p>"); 
	        		 
	        	 }
	        	 
	        	 
	        	 
	        	 
	         }
	         
	         out.println("</body>");
	         out.println("</html>");
	         
	      } catch (JsonProcessingException e) { // TODO
				e.printStackTrace();
			}finally {
	         out.close();  
	      }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	// This method is to get the list of all animals in the zoo
	private String getAllValues() {
		List<Animal> animals = null;
		String result = null;

		try {
			animals = crudserviceImpl.listAllAnimals();
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			objectMapper.setDateFormat(dateFormat);
			result = objectMapper.writeValueAsString(animals);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	//This Method Used To delete an animal from the zoo
	private String deleteAnimal(String requestBody) {
		
		String result = null;
		boolean bresult = false;
		
		try {
			Animal animalObject = new ObjectMapper().readValue(requestBody, Animal.class);
			bresult = crudserviceImpl.deleteAnimal(animalObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To update an animal details
	private String updateAnimal(String requestBody) {
		
		String result = null;
		boolean bresult = false;
		
		try {
			Animal animalObject = new ObjectMapper().readValue(requestBody, Animal.class);
			bresult = crudserviceImpl.updateAnimal(animalObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To add an animal to the zoo
	private String addAnimal(String requestBody) {
		
		String result = null;
		boolean bresult = false;
		
		try {
			Animal animalObject = new ObjectMapper().readValue(requestBody, Animal.class);
			bresult = crudserviceImpl.addAnimal(animalObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To add a room 
	private String addRoom(String requestBody) {
		
		String result = null;
		boolean bresult = false;
		
		try {
			Room roomObject = new ObjectMapper().readValue(requestBody, Room.class);
			bresult = crudserviceImpl.addRoom(roomObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To get an animal details 
	private String readRoom(String requestBody) {
		
		String result = null;
		
		try {
			Room roomObject = new ObjectMapper().readValue(requestBody, Room.class);
			Room roomSelected = crudserviceImpl.findRoomById(roomObject);
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			objectMapper.setDateFormat(dateFormat);
			result = objectMapper.writeValueAsString(roomSelected);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To update a room
	private String updateRoom(String requestBody) {
		
		String result = null;
		boolean bresult = false;
		try {
			Room roomObject = new ObjectMapper().readValue(requestBody, Room.class);
			bresult = crudserviceImpl.updateRoom(roomObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To delete a room
	private String deleteRoom(String requestBody) {
		
		String result = null;
		boolean bresult = false;
		try {
			Room roomObject = new ObjectMapper().readValue(requestBody, Room.class);
			bresult = crudserviceImpl.deleteRoom(roomObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To assign a favorite room to an animal
	private String assignFavouriteRoomtoAnimal(String roomId, String requestBody) {
		
		String result = null;
		boolean bresult = false;
		try {
			Animal animalObject = new ObjectMapper().readValue(requestBody, Animal.class);
			int roomIdInt = (int) Integer.parseInt(roomId);
			bresult = crudserviceImpl.assignFavouriteRoomtoAnimal(roomIdInt, animalObject );
			Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
			resultMap.put("final_result", bresult);
			

			result = Boolean.toString(bresult);
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
			result= "Jsonfalse";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result= "IOfalse";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result= "SQLfalse";
		}

		return result;
	}
	
	//This Method Used To remove the favorite room for an animal
	private String removeFavouriteRoomForAnimal(String roomId, String requestBody) {
		
		String result = null;
		boolean bresult = false;
		try {
			Animal animalObject = new ObjectMapper().readValue(requestBody, Animal.class);
			int roomIdInt = (int) Integer.parseInt(roomId);
			bresult = crudserviceImpl.removeFavouriteRoomForAnimal(roomIdInt, animalObject );
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	//This Method Used To move an animal into a room
	private String moveAnimalToRoom(String animalId, String requestBody) {
		
		String result = null;
		boolean bresult = false;
		try {
			Room roomObject = new ObjectMapper().readValue(requestBody, Room.class);
			int animalIdInt = (int) Integer.parseInt(animalId);
			bresult = crudserviceImpl.moveAnimalToRoom(animalIdInt, roomObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	
	// This Method Used To move an animal out of the room
	private String removeAnimalFromRoom(String animalId, String requestBody) {
		
		String result = null;
		boolean bresult = false;
		try {
			Room roomObject = new ObjectMapper().readValue(requestBody, Room.class);
			int animalIdInt = (int) Integer.parseInt(animalId);
			bresult = crudserviceImpl.removeAnimalFromRoom(animalIdInt, roomObject);
			Map<String, Object> resultMap = new HashMap();
			resultMap.put("result", bresult);
			result = new ObjectMapper().writeValueAsString(resultMap);

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) { // TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	// This Method Used To get report of animals not assigned any room
	private String animalsNotinRoom(String sortBy,String sortOrder ) {
		
		String result = null;
		
		try {
			List<Animal> animalsNotinRoom = reportserviceImpl.animalsNotinRoom(sortBy, sortOrder);
			ObjectMapper objectMapper = new ObjectMapper();
			List<AnimalClone> refinedAnimalList= new ArrayList<AnimalClone>();
			
			if(animalsNotinRoom != null & animalsNotinRoom.size() > 0) {
				for(Animal animalObj : animalsNotinRoom) {
					AnimalClone animalCloneObj = new AnimalClone();
					animalCloneObj.setAnimalName(animalObj.getAnimalName());
					animalCloneObj.setDateOfEntry(animalObj.getDateOfEntry().toString());
					
					refinedAnimalList.add(animalCloneObj);
				}
				}

			result = objectMapper.writeValueAsString(refinedAnimalList);
		} catch (Exception e) {
			
		}
		return result;
	}
	
	// This Method Used To get a report of animals in a specific room
	private String animalsInSpecificRoom(String roomId, String sortBy,String sortOrder ) {
		
		String result = null;
		
		try {
			int roomIdInt = Integer.parseInt(roomId);
			List<Animal> animalsInSpecificRoom = reportserviceImpl.animalsInSpecificRoom(roomIdInt,sortBy, sortOrder);
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			objectMapper.setDateFormat(dateFormat);
			result = objectMapper.writeValueAsString(animalsInSpecificRoom);
		} catch (Exception e) {
			
		}
		return result;
	}
	
	// This Method Used To get report of favorite rooms for an animal
	private String favouriteRoomsForAnimal(String animalId) {
		
		String result = null;
		
		try {
			int animalIdInt = Integer.parseInt(animalId);
			List<Room> favouriteRoomsForAnimal = reportserviceImpl.favouriteRoomsForAnimal(animalIdInt);
			ObjectMapper objectMapper = new ObjectMapper();

			result = objectMapper.writeValueAsString(favouriteRoomsForAnimal);
		} catch(Exception e ) {
			
			
		}
		return result;
	}

}
