package wdw.demo.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity(name="park")
@Table(name="PARK")
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PARK_ID")
    private long parkId;
    @Column(name="PARK_NAME")
    private String parkName;
    @Column(name="SURFACE")
    private int surface;
    @Column(name="OPENING_YEAR")
    private int openingYear;

    @OneToMany(mappedBy = "park", cascade = CascadeType.ALL)
    private List<Attraction> attractions = new ArrayList<Attraction>();


    public Park(String parkName, int surface, int openingYear){
        this.parkName = parkName;
        this.surface = surface;
        this.openingYear = openingYear;

    }

    public void addAttraction(Attraction attraction){
        this.attractions.add(attraction);

        if(attraction.getPark() != null) attraction.getPark().getAttractions().remove(attraction);
        attraction.setPark(this);
    }




}
