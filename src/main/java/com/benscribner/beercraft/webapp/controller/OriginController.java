package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Retrieve a list of origins.
 */
@Controller
@RequestMapping("/admin/origins*")
public class OriginController {

    private static final String VIEW = "admin/originList";

    @Resource(name = "originManager")
    private NamedObjectManager<Origin> originManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView(VIEW, "origins", originManager.getAll());
    }
}
