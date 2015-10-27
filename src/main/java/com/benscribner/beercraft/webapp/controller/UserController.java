package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.impl.UserImpl;
import com.benscribner.beercraft.service.UserManager;

/**
 * Retrieve a list of users.
 */
@Controller
@RequestMapping("/admin/users*")
public class UserController {

    private static final String VIEW = "admin/userList";

    @Resource(name = "userManager")
    private UserManager<UserImpl> userManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        return new ModelAndView(VIEW, "users", userManager.getAll());
    }
}
