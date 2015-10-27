package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Retrieve a list of categories.
 */
@Controller
@RequestMapping("/admin/categories*")
public class CategoryController {

    private static final String VIEW = "admin/categoryList";

    @Resource(name = "categoryManager")
    private NamedObjectManager<Category> categoryManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView(VIEW, "categories", categoryManager.getAll());
    }
}
