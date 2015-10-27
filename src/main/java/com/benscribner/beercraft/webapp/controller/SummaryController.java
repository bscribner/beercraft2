package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.service.OrderManager;

/**
 * Display an order
 */
@Controller
@RequestMapping("/summary*")
public class SummaryController extends BaseController {

    private static final String VIEW = "summary";

    @Resource(name = "orderManager")
    private OrderManager orderManager;

    /**
     * Display the order
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView order(@RequestParam("orderId") final String orderId, final HttpServletRequest request)
            throws Exception {
        return new ModelAndView(VIEW, "order", orderManager.get(orderId));
    }
}