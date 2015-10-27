package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.service.IdentifiableManager;

/**
 * Show a user's shopping cart
 */
@Controller
@RequestMapping("/cart*")
public class ShoppingCartController extends BaseController {

    private static final String VIEW = "cart";
    private static final String REDIRECT_VIEW = "redirect:cart";

    @Resource(name = "lineItemManager")
    private IdentifiableManager<LineItem> lineItemManager;

    /**
     * Display the cart
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView cart(final HttpServletRequest request) throws Exception {
        return new ModelAndView(VIEW, "cart", getShoppingCart(request));
    }

    /**
     * Delete an item in the cart
     * 
     * @param lineItemId
     *            the lineItemId to delete
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the return view
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, params = "lineItemId")
    public String delete(@RequestParam("lineItemId") final String lineItemId, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        final LineItem item = lineItemManager.get(lineItemId);

        final Cart cart = getShoppingCart(request);

        if (item != null) {
            cart.getLineItems().remove(item);
            shoppingCartManager.save(cart);

            saveMessage(request, "The beer has been removed from your cart.");
        }

        return REDIRECT_VIEW;
    }
}