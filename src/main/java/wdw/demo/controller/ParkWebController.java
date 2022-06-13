package wdw.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wdw.demo.model.Park;
import wdw.demo.service.ParkService;

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
    public String showParkUpdateForm(Model model, Long parkId){
        Park parkToUpdate = parkService.findParkById(parkId).get();
        model.addAttribute("park", parkToUpdate);
        return "updatepark";
    }

    @RequestMapping("/updatePark")
    public String updatePark(Park park) {
        parkService.updatePark(park);
        return "redirect:/myresort/home";

    }

    @RequestMapping("/deletePark")
    public String deletePark(@RequestParam Long parkIdFromView){

        parkService.deleteParkById(parkIdFromView).getParkName();

        return "redirect:/myresort/home";
    }

}
