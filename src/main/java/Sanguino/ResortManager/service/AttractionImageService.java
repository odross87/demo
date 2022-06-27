package Sanguino.ResortManager.service;

import Sanguino.ResortManager.model.AttractionImage;
import Sanguino.ResortManager.model.ParkImage;
import Sanguino.ResortManager.repository.AttractionImageRepository;
import Sanguino.ResortManager.repository.ParkImageRepository;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class AttractionImageService {

    @Autowired
    AttractionImageRepository attractionImageRepository;

    public AttractionImage uploadAttractionImage(String name, MultipartFile file) throws IOException {

        AttractionImage attractionImage  = new AttractionImage();
        attractionImage.setName(name);
        attractionImage.setImage( new Binary(file.getBytes() ));

        attractionImageRepository.save(attractionImage);

        return attractionImage;

    }

    public String getDataAttractionImage(String id){

        Optional<AttractionImage> attractionImage = attractionImageRepository.findById(id);
        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString( attractionImage.get().getImage().getData() );

    }


    public ResponseEntity<byte[]> getAttractionImage(String id){

        Optional<AttractionImage> attractionImage = attractionImageRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>( attractionImage.get().getImage().getData(), headers, HttpStatus.OK );

    }

    public ResponseEntity<byte[]> getAttractionImageByName(String name){

        Optional<AttractionImage> attractionImage = attractionImageRepository.findByName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>( attractionImage.get().getImage().getData(), headers, HttpStatus.OK );

    }


}
