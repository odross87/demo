package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.model.AttractionImage;
import Sanguino.ResortManager.service.AttractionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("apiAttractionImage")
public class AttractionImageRestController {

    @Autowired
    AttractionImageService attractionImageService;


    @PostMapping("/uploadImage")
    public AttractionImage saveBookImage(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {


        return attractionImageService.uploadAttractionImage(name, file);

    }

    @GetMapping("/getDataImage")
    public String getDataAttractionRestImage(@RequestParam  String id){

        return attractionImageService.getDataAttractionImage(id);

    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getAttractionRestImage(@RequestParam String id){


        return attractionImageService.getAttractionImage(id);

    }

    @GetMapping("/getImageByName")
    public ResponseEntity<byte[]> getAttractionRestImageByName(@RequestParam String name){


        return attractionImageService.getAttractionImageByName(name);

    }
}
