package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.model.ParkImage;
import Sanguino.ResortManager.service.ParkImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class ParkImageRestController {

    @Autowired
    ParkImageService parkImageService;


    @PostMapping("/uploadImage")
    public ParkImage saveBookImage(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {


        return parkImageService.uploadParkImage(name, file);

    }

    @GetMapping("/getDataImage")
    public String getDataParkRestImage(@RequestParam  String id){

        return parkImageService.getDataParkImage(id);

    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getParkRestImage(@RequestParam String id){


        return parkImageService.getParkImage(id);

    }

    @GetMapping("/getImageByName")
    public ResponseEntity<byte[]> getParkRestImageByName(@RequestParam String name){


        return parkImageService.getParkImage(name);

    }
}
