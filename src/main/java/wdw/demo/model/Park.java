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
    private long park_id;
    @Column(name="PARK_NAME")
    private String park_name;
    @Column(name="SURFACE")
    private int surface;
    @Column(name="OPENING_YEAR")
    private int openingYear;

    @OneToMany(mappedBy = "park", cascade = CascadeType.ALL)
    private List<Ride> rides = new ArrayList<Ride>();


    public Park(String park_name, int surface, int openingYear){
        this.park_name = park_name;
        this.surface = surface;
        this.openingYear = openingYear;

    }

    public void addRide(Ride ride){
        this.rides.add(ride);

        if(ride.getPark() != null) ride.getPark().getRides().remove(ride);
        ride.setPark(this);
    }




}
