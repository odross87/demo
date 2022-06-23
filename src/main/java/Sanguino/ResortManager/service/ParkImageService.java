package Sanguino.ResortManager.service;

import Sanguino.ResortManager.model.ParkImage;
import Sanguino.ResortManager.repository.ParkImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkImageService {

    @Autowired
    ParkImageRepository parkImageRepository;

    public ParkImage uploadParkImage(ParkImage parkImage){

        ParkImage parkImageUploaded = parkImageRepository.save(parkImage);

        return parkImageUploaded;
    }

}
