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
@Document("AttractionImage")
public class AttractionImage {
    @Id
    private String id;
    private String name;
    private Binary image;


}
