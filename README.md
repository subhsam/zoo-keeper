# zoo-keeper

#Table creation scripts

CREATE DATABASE [IF NOT EXISTS] zooDb;

USE zooDb;
 
DROP TABLE IF EXISTS animal;
 
CREATE TABLE IF NOT EXISTS animal (
  animal_id int(100) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  animal_name varchar(50) DEFAULT NULL,
  date_of_entry date,  
  room_id_assigned int(100), CONSTRAINT fk_room_id_assigned
    FOREIGN KEY (room_id_assigned) 
        REFERENCES room(room_id);

USE zooDb;
 
DROP TABLE IF EXISTS room;
 
CREATE TABLE IF NOT EXISTS room (
  room_id int(100) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  room_name varchar(50) DEFAULT NULL,
  date_of_creation date, 
  animal_id_favourite int(100),
 
  CONSTRAINT fk_animal_id
    FOREIGN KEY (animal_id_favourite) 
        REFERENCES animal(animal_id)
);

# Requests made to controller - URL followed by body

http://localhost:8080/zookeeper-web-project/myservlet?action=getAllValues
http://localhost:8080/zookeeper-web-project/myservlet?action=deleteAnimal
{"animalId" : "5"}

http://localhost:8080/zookeeper-web-project/myservlet?action=updateAnimal
{"animalId" : "7","animalName":"Tiger","dateOfEntry":"2017-10-21"}

http://localhost:8080/zookeeper-web-project/myservlet?action=addAnimal
{"animalName":"Zebra","dateOfEntry":"2018-08-21"}

http://localhost:8080/zookeeper-web-project/myservlet?action=addRoom
{"roomName":"Room1","dateOfCreation":"2019-10-22"}


http://localhost:8080/zookeeper-web-project/myservlet?action=readRoom
{"roomId":"4"}

http://localhost:8080/zookeeper-web-project/myservlet?action=deleteRoom
{"roomId":"4"}

http://localhost:8080/zookeeper-web-project/myservlet?action=updateRoom
{"roomId":"3","dateOfCreation":"2017-09-12"}

http://localhost:8080/zookeeper-web-project/myservlet?action=assignFavouriteRoomtoAnimal&roomId=3
{"animalId":"7","dateOfEntry":"2017-10-21"}

http://localhost:8080/zookeeper-web-project/myservlet?action=removeFavouriteRoomForAnimal&roomId=3
{"animalId":"7","dateOfEntry":"2017-10-21"}

http://localhost:8080/zookeeper-web-project/myservlet?action=moveAnimalToRoom&animalId=7
{"roomId":"2","dateOfCreation":"2019-10-23"}

http://localhost:8080/zookeeper-web-project/myservlet?action=removeAnimalFromRoom&animalId=7

{"roomId":"2","dateOfCreation":"2019-10-23"}


http://localhost:8080/zookeeper-web-project/myservlet?getReport=animalsNotinRoom
{"sortBy":"SORT_BY_TITLE","sortOrder":"DESC"}


http://localhost:8080/zookeeper-web-project/myservlet?getReport=animalsInSpecificRoom
{"roomId":"1","sortBy":"SORT_BY_LOCATED","sortOrder":"ASC"}


http://localhost:8080/zookeeper-web-project/myservlet?getReport=favouriteRoomsForAnimal
{"animalId":"6"}
