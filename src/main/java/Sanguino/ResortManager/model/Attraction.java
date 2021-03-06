package Sanguino.ResortManager.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.annotation.Id;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Document("Attractions")
public class Attraction{

    @Id
    private String _id;
    private String attractionName;
    private String attractionDescription;

    private Park park;

    public Attraction(String name, String description, Park park){
        this.attractionName = name;
        this.attractionDescription = description;
        this.park = park;
    }


}
