package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Retrieve a list of Breweries.
 */
@Controller
@RequestMapping("/admin/breweries*")
public class BreweryController {

    private static final String VIEW = "admin/breweryList";

    @Resource(name = "breweryManager")
    private NamedObjectManager<Brewery> breweryManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView(VIEW, "breweries", breweryManager.getAll());
    }
}
