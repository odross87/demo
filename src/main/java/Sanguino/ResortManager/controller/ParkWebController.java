package Sanguino.ResortManager.controller;


import Sanguino.ResortManager.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Sanguino.ResortManager.model.Park;

@Controller
@RequestMapping("/park")
public class ParkWebController {

    @Autowired
    ParkService parkService;

    @RequestMapping("/createParkForm")
    public String showParkForm(Model model){

        model.addAttribute("park", new Park());

        return "newpark";
    }

    @RequestMapping("/createpark")
    public String createPark(Park park) {

        parkService.createPark(park);

        return "redirect:/myresort/home";
    }

    @RequestMapping("/updateParkForm")
    public String showParkUpdateForm(Model model, String parkIdFromView){
        Park parkToUpdate = parkService.findParkById(parkIdFromView).get();
        model.addAttribute("park", parkToUpdate);
        return "updatepark";
    }

    @RequestMapping("/updatePark")
    public String updatePark(Park park) {
        parkService.updatePark(park);
        return "redirect:/myresort/home";

    }

    @RequestMapping("/deletePark")
    public String deletePark(@RequestParam String parkIdFromView){

        parkService.deleteParkById(parkIdFromView).getParkName();

        return "redirect:/myresort/home";
    }

}
