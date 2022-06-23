package Sanguino.ResortManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import Sanguino.ResortManager.service.ParkService;
import Sanguino.ResortManager.service.AttractionService;
import java.util.Scanner;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    ParkService parkService;

    @Autowired
    AttractionService attractionService;

    @Override
    public void run(String... args) throws Exception {

        Scanner reader = new Scanner(System.in);

    }


}