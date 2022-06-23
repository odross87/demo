package Sanguino.ResortManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Sanguino.ResortManager.model.Attraction;
import Sanguino.ResortManager.service.AttractionService;
import Sanguino.ResortManager.service.ParkService;

@Controller
@RequestMapping("/attraction")
public class AttractionWebController {

    @Autowired
    AttractionService attractionService;
    @Autowired
    ParkService parkService;

    @RequestMapping("/createAttractionForm")
    public String showAttractionForm(Model model) {

        model.addAttribute("attraction", new Attraction());
        model.addAttribute("parksfromController",
                parkService.listOfParks());

        return "newattraction";
    }

    @RequestMapping("/createAttraction")
    public String createAttraction(Attraction attraction) {
        attractionService.createAttraction(attraction);

        return "redirect:/myresort/home";
    }

    //Todo Update Attraction
    @RequestMapping("/updateAttractionForm")
    public String showAttractionUpdateForm(Model model, String attractionId){

        Attraction attractionToUpdate = attractionService.getAttractionById(attractionId).get();
        model.addAttribute("attraction", attractionToUpdate);
        model.addAttribute("parksfromController",
                parkService.listOfParks());

        return "updateattraction";
    }


    @RequestMapping("/updateAttraction")
    public String updateAttraction(Attraction attraction) {

        attractionService.updateAttraction(attraction);

        return "redirect:/myresort/home";

    }


    @RequestMapping("/deleteAttraction")
    public String deleteAttraction(@RequestParam String attractionId){

        attractionService.deleleteAttraction(attractionId);

        return "redirect:/myresort/home";


    }

}