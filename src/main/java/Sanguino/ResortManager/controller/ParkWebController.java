package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.model.ParkImage;
import Sanguino.ResortManager.service.ParkImageService;
import Sanguino.ResortManager.service.ParkService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Sanguino.ResortManager.model.Park;
import org.springframework.web.multipart.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/park")
public class ParkWebController {

    @Autowired
    ParkService parkService;
    @Autowired
    ParkImageService parkImageService;

    @RequestMapping("/createParkForm")
    public String showParkForm(Model model){

        model.addAttribute("park", new Park());

        return "newpark";
    }

    @RequestMapping("/createpark")
    public String createPark(Park park, MultipartFile file) throws IOException {


        Park parkCreated = parkService.createPark(park);
        parkImageService.uploadParkImage(parkCreated.get_id().toString(), file);

        return "redirect:/myresort/home";
    }

    @RequestMapping("/updateParkForm")
    public String showParkUpdateForm(Model model, String parkIdFromView){
        Park parkToUpdate = parkService.findParkById(parkIdFromView).get();
        model.addAttribute("park", parkToUpdate);
        return "updatepark";
    }

    @RequestMapping("/updatePark")
    public String updatePark(Park park, String id, MultipartFile file) throws IOException {

        Optional<Park> parkFound = parkService.findParkById(id);

        if (parkFound.isPresent()) {

            Park parkToUpdate = parkFound.get();

            if  (park.getParkName() != null) {
                parkToUpdate.setParkName(park.getParkName());
            }
            if  (park.getParkDescription() != null) {
                parkToUpdate.setParkDescription(park.getParkDescription());
            }
            if  (park.getSurface() != 0) {
                parkToUpdate.setSurface(park.getSurface());
            }
            if (park.getOpeningYear() != 0){
                parkToUpdate.setOpeningYear(park.getOpeningYear());
            }
            if (!file.isEmpty()){
                parkImageService.deleteParkImagebyName(parkToUpdate.get_id());

                parkImageService.uploadParkImage(parkToUpdate.get_id().toString(), file);
            }

            parkService.updatePark(parkToUpdate);



            return "redirect:/myresort/home";

        } else  return "redirect:/myresort/home";

    }

    @RequestMapping("/deletePark")
    public String deletePark(@RequestParam String parkIdFromView){

        parkService.deleteParkById(parkIdFromView).getParkName();

        return "redirect:/myresort/home";
    }




}
