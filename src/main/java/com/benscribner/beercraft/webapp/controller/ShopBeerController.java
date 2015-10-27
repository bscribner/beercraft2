package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.impl.LineItemObject;
import com.benscribner.beercraft.service.BeerManager;
import com.benscribner.beercraft.service.CartManager;
import com.benscribner.beercraft.webapp.editor.IdentifiableEditor;

/**
 * Display an individual Beer and allow it to be added to a cart
 */
@Controller
@RequestMapping("/beer*")
public class ShopBeerController extends BaseController {

    private static final String VIEW = "beer";
    private static final String SHOP_VIEW = "redirect:shop";

    @Resource(name = "beerManager")
    private BeerManager beerManager;

    @Resource(name = "cartManager")
    private CartManager shoppingCartManager;

    /**
     * @see com.benscribner.beercraft.webapp.controller.BaseFormController#initBinder(javax.servlet.http.HttpServletRequest,
     * org.springframework.web.bind.ServletRequestDataBinder)
     */
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Beer.class, new IdentifiableEditor<Beer>(beerManager));
    }

    /**
     * Display a beer
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView beer(@RequestParam("id") final Beer beer) throws Exception {
        final ModelAndView mav = new ModelAndView(VIEW);
        final LineItemObject item = new LineItemObject(beer);

        mav.addObject("beer", beer);
        mav.addObject("lineItem", item);

        return mav;
    }

    /**
     * Add a line item to the shopping cart
     * 
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        log.debug("entering 'onSubmit' method...");

        try {
            final String beerId = request.getParameter("beer.id");
            final String quantity = request.getParameter("quantity");

            final LineItemObject lineItem = new LineItemObject();
            lineItem.setBeer(beerManager.get(beerId));
            lineItem.setQuantity(new Integer(quantity));

            final Cart cart = getShoppingCart(request);
            cart.getLineItems().add(lineItem);

            shoppingCartManager.save(cart);

            saveMessage(request, "The beer has been added to your cart.");
        } catch (final Exception e) {
            return VIEW;
        }

        return SHOP_VIEW;
    }
}