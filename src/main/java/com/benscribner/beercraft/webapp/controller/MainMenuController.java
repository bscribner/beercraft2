package com.benscribner.beercraft.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Retrieves the general information from the database
 */
@Controller
@RequestMapping("/main")
public class MainMenuController {

    /**
     * Display the page
     * 
     * @return the view
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView("mainMenu");
    }
}
