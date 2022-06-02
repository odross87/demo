package wdw.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wdw.demo.model.Park;
import wdw.demo.service.ParkService;

@Controller
@RequestMapping("/park")
public class ParkWebController {

    @Autowired
    ParkService parkService;

    @RequestMapping("/form")
    public String showParkForm(Model model){
        model.addAttribute("park", new Park());
        return "newpark";
    }

    @RequestMapping("/formUpdate")
    public String showParkUpdateForm(Model model, Long parkId){
        Park parkToUpdate = parkService.findParkById(parkId).get();
        model.addAttribute("park", parkToUpdate);
        return "updatepark";
    }

    @RequestMapping("/createpark")
    public String createPark(Park park, BindingResult result) {
        ModelAndView model = new ModelAndView();
        parkService.createPark(park);
        model.addObject("park", park);
        model.setViewName(result.hasErrors() ? "parkForm" : "parkReady");

        return "deletedpark";
    }
    @RequestMapping("/updatepark")
    public String updatePark(Park park, BindingResult result) {
        ModelAndView model = new ModelAndView();
        parkService.updatePark(park);
        model.addObject("park", park);
        model.setViewName(result.hasErrors() ? "parkForm" : "parkReady");

        return "deletedpark";
    }


}
