package Sanguino.ResortManager.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Document("Hotels")
public class Hotel {

    @Id
    private String _id;
    private String hotelName;
    private String hotelStars;
    private int openingYear;
    private int numOfRooms;
    private int numOfPools;


    public Hotel(String name, String stars, int year, int rooms, int pools){
        this.hotelName = name;
        this.hotelStars = stars;
        this.openingYear = year;
        this.numOfRooms = rooms;
        this.numOfPools = pools;
    }


}
