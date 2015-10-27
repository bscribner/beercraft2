package com.benscribner.beercraft.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.CreditCardType;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.impl.ConcreteCreditCard;
import com.benscribner.beercraft.model.impl.LineItemObject;
import com.benscribner.beercraft.model.impl.PurchaseOrder;
import com.benscribner.beercraft.service.OrderManager;

/**
 * Handle the payment information
 */
@Controller
@RequestMapping("/payment*")
public class PaymentController extends BaseController {

    private static final String FORM_VIEW = "payment";
    private static final String SUMMARY_VIEW = "redirect:summary";
    private static final String CART_VIEW = "redirect:cart";

    @Resource(name = "orderManager")
    private OrderManager orderManager;

    /**
     * @see com.benscribner.beercraft.webapp.controller.BaseFormController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /**
     * Provide a new object to the form
     * 
     * @param request
     *            the request object
     * @return an order
     * @throws Exception
     */
    @ModelAttribute("order")
    public Order order(final HttpServletRequest request) throws Exception {
        final Cart cart = getShoppingCart(request);

        PurchaseOrder order = null;
        final String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            order = (PurchaseOrder) orderManager.get(id);

            order.setCardNumber(new ConcreteCreditCard());

        } else {
            order = new PurchaseOrder();
            order.setPerson(cart.getPerson());

            for (final LineItem lineItem : cart.getLineItems()) {
                final LineItemObject orderItem = new LineItemObject();
                orderItem.setBeer(lineItem.getBeer());
                orderItem.setQuantity(lineItem.getQuantity());
                order.getLineItems().add(orderItem);
            }

            orderManager.save(order);

            order.setCardNumber(new ConcreteCreditCard());
        }

        return order;
    }

    /**
     * Display the order form
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView orderForm() {
        final ModelAndView mav = new ModelAndView(FORM_VIEW);
        mav.addObject("cardTypes", CreditCardType.values());

        return mav;
    }

    /**
     * Handle the form submission
     * 
     * @param order
     *            the order object bound to the form
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("order") final Order order, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        log.debug("entering 'onSubmit' method...");

        if (request.getParameter("cancel") != null) {
            return CART_VIEW;
        }

        if (errors.hasErrors()) {
            validationErrorMessage(request);

            // fix validation of price
            final ObjectError typeError = errors.getFieldError("cardNumber.type");
            if (typeError != null && typeError.getDefaultMessage().contains("ConversionFailedException")) {
                redefineErrorMessage(typeError, "Card Type is required");
            }

            return FORM_VIEW;
        }

        try {
            orderManager.save(order);

            saveMessage(request, "Your order has been processed.");
        } catch (final Exception e) {
            e.printStackTrace();

            return FORM_VIEW;
        }

        // clear out the shopping cart
        try {
            final Cart cart = getShoppingCart(request);
            cart.getLineItems().clear();
            shoppingCartManager.save(cart);
        } catch (final Exception e) {
            e.printStackTrace();

        }

        return SUMMARY_VIEW + "?orderId=" + order.getId();
    }
}