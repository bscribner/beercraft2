package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Retrieve a list of beer.
 */
@Controller
@RequestMapping("/admin/beerlist*")
public class BeerController {

    private static final String VIEW = "admin/beerList";

    @Resource(name = "beerManager")
    private NamedObjectManager<Beer> beerManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView(VIEW, "beerList", beerManager.getAll());
    }
}
