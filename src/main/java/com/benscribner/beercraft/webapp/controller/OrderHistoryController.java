package com.benscribner.beercraft.webapp.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.service.OrderManager;

/**
 * Show the order history
 */
@Controller
@RequestMapping("/history*")
public class OrderHistoryController extends BaseController {

    private static final String VIEW = "history";
    private static final String MAIN = "redirect:main";

    @Resource(name = "orderManager")
    private OrderManager orderManager;

    /**
     * Display the cart
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView history(final HttpServletRequest request) throws Exception {

        final Principal principal = request.getUserPrincipal();

        // check if user is logged in
        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            // retrieve the user
            final User user = (User) userManager.loadUserByUsername(principal.getName());

            return new ModelAndView(VIEW, "orders", orderManager.getForPerson(user.getPerson()));

        } else {
            return new ModelAndView(MAIN);
        }
    }
}