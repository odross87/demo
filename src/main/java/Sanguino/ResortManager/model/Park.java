package Sanguino.ResortManager.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Document("Parks")
public class Park{

    @Id
    private String _id;

    private String parkName;

    private int surface;
    private int openingYear;



    public Park(String parkName, int surface, int openingYear){
        this.parkName = parkName;
        this.surface = surface;
        this.openingYear = openingYear;

    }






}
