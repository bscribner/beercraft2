package com.benscribner.beercraft.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Start the checkout
 */
@Controller
@RequestMapping("/checkout*")
public class CheckoutController extends BaseController {

    private static final String VIEW = "checkout";

    /**
     * Display the cart
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cart(final HttpServletRequest request) throws Exception {
        return new ModelAndView(VIEW, "cart", getShoppingCart(request));
    }
}