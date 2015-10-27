package com.benscribner.beercraft.webapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Retrieves the categories, breweries, and origins from the database
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Resource(name = "categoryManager")
    private NamedObjectManager<Category> categoryManager;

    @Resource(name = "breweryManager")
    private NamedObjectManager<Brewery> breweryManager;

    @Resource(name = "originManager")
    private NamedObjectManager<Origin> originManager;

    /**
     * Retrieve all the categories
     * 
     * @return a list of all the categories
     */
    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryManager.getAll();
    }

    /**
     * Retrieve all the breweries
     * 
     * @return a list of all the breweries
     */
    @ModelAttribute("breweries")
    public List<Brewery> breweries() {
        return breweryManager.getAll();
    }

    /**
     * Retrieve all the origins
     * 
     * @return a list of all the origins
     */
    @ModelAttribute("origins")
    public List<Origin> origins() {
        return originManager.getAll();
    }

    /**
     * Display the page
     * 
     * @return the view
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView("shop");
    }
}
