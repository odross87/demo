package wdw.demo.model;

import lombok.*;
import javax.persistence.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor

@Entity(name = "ride")
@Table(name = "Ride")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ride_id;

    @Column(name = "RIDE_NAME")
    private String ride_name;

    @Column(name= "RIDE_TYPE")
    private String ride_type;

    @Column(name= "RIDE_DESCRIPTION")
    private String ride_description;

    @ManyToOne()
    @JoinColumn(name = "PARK_ID")
    private Park park;

    public Ride(String name, String type, String description, Park park){
        this.ride_name = name;
        this.ride_type = type;
        this.ride_description = description;
        this.park = park;
    }


}
