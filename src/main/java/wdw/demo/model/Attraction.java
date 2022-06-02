package wdw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor

@Entity(name = "attraction")
@Table(name = "ATTRACTION")
public class Attraction{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attractionId;

    @Column(name = "ATTRACTION_NAME")
    private String attractionName;

    @Column(name= "ATTRACTION_DESCRIPTION")
    private String attractionDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="park")
    private Park park;

    public Attraction(String name, String description){
        this.attractionName = name;
        this.attractionDescription = description;
    }

    public Attraction(String name, String description, Park park){
        this.attractionName = name;
        this.attractionDescription = description;
        this.park = park;
        park.addAttraction(this);
    }

}
