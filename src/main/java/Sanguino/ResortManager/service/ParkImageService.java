package Sanguino.ResortManager.service;

import Sanguino.ResortManager.model.ParkImage;
import Sanguino.ResortManager.repository.ParkImageRepository;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class ParkImageService {

    @Autowired
    ParkImageRepository parkImageRepository;

    public ParkImage uploadParkImage(String name, MultipartFile file) throws IOException {

        ParkImage parkImage  = new ParkImage();
        parkImage.setName(name);
        parkImage.setImage( new Binary(file.getBytes() ));

        parkImageRepository.save(parkImage);

        return parkImage;

    }

    public String getDataParkImage(String id){

        Optional<ParkImage> bookImage = parkImageRepository.findById(id);
        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString( bookImage.get().getImage().getData() );

    }


    public ResponseEntity<byte[]> getParkImage(String id){

        Optional<ParkImage> parkImage = parkImageRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>( parkImage.get().getImage().getData(), headers, HttpStatus.OK );

    }

    public ResponseEntity<byte[]> getParkImageByName(String name){

        Optional<ParkImage> parkImage = parkImageRepository.findByName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>( parkImage.get().getImage().getData(), headers, HttpStatus.OK );

    }

    public void deleteParkImagebyName(String name){

        Optional<ParkImage> parkImage = parkImageRepository.findByName(name);
        if (parkImage.isPresent()){
            ParkImage parkImageToDelete = parkImage.get();
            parkImageRepository.deleteById(parkImageToDelete.getId());
        }

    }


}
