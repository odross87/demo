package Sanguino.ResortManager.model;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("ParkImage")
public class ParkImage {
    @Id
    private String id;
    private String name;
    private Binary image;


}
