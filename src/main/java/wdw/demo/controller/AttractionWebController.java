package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wdw.demo.model.Attraction;
import wdw.demo.service.AttractionService;
import wdw.demo.service.ParkService;

@Controller
@RequestMapping("/attraction")
public class AttractionWebController {

    @Autowired
    AttractionService attractionService;
    @Autowired
    ParkService parkService;

    @RequestMapping("/form")
    public String showAttractionForm(Model model) {
        model.addAttribute("attraction", new Attraction());
        model.addAttribute("parksfromController",
                parkService.listOfParks());

        return "newattraction";
    }

    @RequestMapping("/createattraction")
    public String createAttraction(Attraction attraction, BindingResult result) {
        ModelAndView model = new ModelAndView();
        attractionService.createAttraction(attraction);
        model.addObject("attraction", attraction);
        model.setViewName(result.hasErrors() ? "attractionForm" : "attractionReady");

        return "deletedattraction";
    }

    @RequestMapping("/deleteAttraction")
    public String deleteAttraction(@RequestParam Long idFromView){

        attractionService.deleleteAttraction(idFromView);

        return "deletedattraction";


    }

}