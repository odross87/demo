package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.model.Park;
import Sanguino.ResortManager.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Sanguino.ResortManager.service.AttractionService;

import java.util.Optional;

@Controller
@RequestMapping("/myresort")
public class MyResortWebController {

    @Autowired
    ParkService parkService;
    @Autowired
    AttractionService attractionService;

    @RequestMapping("/home")
    public String getWeb (Model containerToView) {
        containerToView.addAttribute("parksfromController",
                parkService.listOfParks());
        containerToView.addAttribute("attractionsfromController",
                attractionService.ListAllAttractions());

        return "web";
    }

    @RequestMapping("/detailPark")
    public String getDetailPark (String id, Model containerToView) {
        Optional<Park> parkToFound= parkService.findParkById(id);
        containerToView.addAttribute("attractionsfromController",
                attractionService.ListAllAttractions());

        if (parkToFound.isPresent()){
             Park park = parkToFound.get();
            containerToView.addAttribute("park",
                    park);
            containerToView.addAttribute("attractionsfromController",
                    attractionService.ListAllAttractionsFromAPark(park.getParkName()));

            return "parkDetail";
        }



        return "web";
    }







}