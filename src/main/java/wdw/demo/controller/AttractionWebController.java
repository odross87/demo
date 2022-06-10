package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wdw.demo.model.Attraction;
import wdw.demo.model.Park;
import wdw.demo.service.AttractionService;
import wdw.demo.service.ParkService;

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
    public String createAttraction(Attraction attraction, BindingResult result) {

        attractionService.createAttraction(attraction);

        return "redirect:/myresort/home";
    }

    //Todo Update Attraction
    @RequestMapping("/updateAttractionForm")
    public String showAttractionUpdateForm(Model model, Long attractionId){

        Attraction attractionToUpdate = attractionService.getAttractionByID(attractionId).get();
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
    public String deleteAttraction(@RequestParam Long idFromView){

        attractionService.deleleteAttraction(idFromView);

        return "redirect:/myresort/home";


    }

}