package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.model.Park;
import Sanguino.ResortManager.service.AttractionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Sanguino.ResortManager.model.Attraction;
import Sanguino.ResortManager.service.AttractionService;
import Sanguino.ResortManager.service.ParkService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/attraction")
public class AttractionWebController {

    @Autowired
    AttractionService attractionService;
    @Autowired
    ParkService parkService;

    @Autowired
    AttractionImageService attractionImageService;

    @RequestMapping("/createAttractionForm")
    public String showAttractionForm(Model model) {

        model.addAttribute("attraction", new Attraction());
        model.addAttribute("parksfromController",
                parkService.listOfParks());

        return "newattraction";
    }

    @RequestMapping("/createAttraction")
    public String createAttraction(Attraction attraction, MultipartFile file) throws IOException {
        Attraction attractionCreated = attractionService.createAttraction(attraction);
        attractionImageService.uploadAttractionImage(attractionCreated.get_id().toString(), file);


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
    public String updateAttraction(Attraction dataAttraction, String id, MultipartFile file) throws IOException {

        Optional<Attraction> attractionToUpdate = attractionService.getAttractionById(id);

        if (attractionToUpdate.isPresent()){

            Attraction attractionFound = attractionToUpdate.get();

            if(dataAttraction.getAttractionName() != null) {
                attractionFound.setAttractionName(dataAttraction.getAttractionName());
            }

            if(dataAttraction.getAttractionDescription() != null){
                attractionFound.setAttractionDescription(dataAttraction.getAttractionDescription());
            }

            if(dataAttraction.getPark() != null){
                attractionFound.setPark(dataAttraction.getPark());
            }

            if (!file.isEmpty() || file != null){
                attractionImageService.deleteParkImagebyName(attractionFound.get_id());

                attractionImageService.uploadAttractionImage(attractionFound.get_id().toString(), file);
            }

            Attraction attractionUpdated = attractionService.updateAttraction(attractionFound);

        }


        return "redirect:/myresort/home";

    }


    @RequestMapping("/deleteAttraction")
    public String deleteAttraction(@RequestParam String attractionId, String parkId){

        attractionService.deleleteAttraction(attractionId);

        return "redirect:/myresort/home";


    }

}