package wdw.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wdw.demo.service.AttractionService;
import wdw.demo.service.ParkService;

@Controller
@RequestMapping("/myresort")
public class WdwWebController {

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

    @RequestMapping("/deletePark")
    public String deletePark(@RequestParam String nameFromView){

        System.out.println("parkName" + nameFromView);

        return "parkdeleted";


    }


}