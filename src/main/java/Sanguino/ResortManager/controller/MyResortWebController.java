package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Sanguino.ResortManager.service.AttractionService;

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







}