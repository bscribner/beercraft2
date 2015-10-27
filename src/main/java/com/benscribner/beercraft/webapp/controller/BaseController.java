package com.benscribner.beercraft.webapp.controller;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import com.benscribner.beercraft.Constants;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.LineItemObject;
import com.benscribner.beercraft.model.impl.ShoppingCart;
import com.benscribner.beercraft.service.CartManager;
import com.benscribner.beercraft.service.UserManager;

/**
 * Base controller
 */
public abstract class BaseController {

    protected final transient Log log = LogFactory.getLog(getClass());

    private static final String SUCCESS_MESSAGES_KEY = "successMessages";
    private static final String ERROR_MESSAGES_KEY = "errorMessages";

    @Resource(name = "userManager")
    protected UserManager<User> userManager;

    @Resource(name = "cartManager")
    protected CartManager shoppingCartManager;

    /**
     * Get a shopping cart for a logged in user or from the session if an anonymous user
     * 
     * @param request the request object
     * @return the cart
     */
    protected Cart getShoppingCart(final HttpServletRequest request) {

        // get cart from session, if it exists
        final String cartId = (String) request.getSession().getAttribute(Constants.CART_ID);

        Cart sessionCart = null;
        if (StringUtils.isNotBlank(cartId)) {
            sessionCart = shoppingCartManager.get(cartId);
        }

        // get username
        final Principal principal = request.getUserPrincipal();

        Cart cart = null;

        // check if user is logged in
        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            // retrieve the user
            final User user = (User) userManager.loadUserByUsername(principal.getName());

            // retrieve the user's cart
            cart = shoppingCartManager.getForPerson(user.getPerson());

            // if cart null, create a new cart
            if (cart == null) {
                cart = new ShoppingCart();
                ((ShoppingCart) cart).setPerson(user.getPerson());

                shoppingCartManager.save(cart);
            }

            // check if session cart has items in it
            if (sessionCart != null && !sessionCart.getLineItems().isEmpty()) {

                // copy items to user's cart
                for (final LineItem lineItem : sessionCart.getLineItems()) {
                    final LineItemObject item = new LineItemObject();
                    item.setBeer(lineItem.getBeer());
                    item.setQuantity(lineItem.getQuantity());
                    cart.getLineItems().add(item);
                }

                // save user's cart
                shoppingCartManager.save(cart);

                // remove session cart
                shoppingCartManager.remove(sessionCart);

                // remove session cart from session
                request.getSession().removeAttribute(Constants.CART_ID);
            }

        } else {
            if (sessionCart == null) {
                cart = new ShoppingCart();
                shoppingCartManager.save(cart);

                request.getSession().setAttribute(Constants.CART_ID, cart.getId());
            } else {
                cart = sessionCart;
            }
        }

        return cart;
    }

    /**
     * Add an error message to the session
     * 
     * Derived from https://github.com/appfuse/appfuse/blob/master/web/spring/src/main/java/org/appfuse/webapp/controller/BaseFormController.java
     * 
     * @param request
     * @param error
     */
    @SuppressWarnings("unchecked")
    protected void saveError(final HttpServletRequest request, final String error) {
        List<String> errors = (List<String>) request.getSession().getAttribute(ERROR_MESSAGES_KEY);
        if (errors == null) {
            errors = new ArrayList<String>();
        }
        errors.add(error);
        request.getSession().setAttribute(ERROR_MESSAGES_KEY, errors);
    }

    /**
     * Add a message to the session
     * 
     * Derived from https://github.com/appfuse/appfuse/blob/master/web/spring/src/main/java/org/appfuse/webapp/controller/BaseFormController.java
     * 
     * @param request
     * @param msg
     */
    @SuppressWarnings("unchecked")
    protected void saveMessage(final HttpServletRequest request, final String msg) {
        List<String> messages = (List<String>) request.getSession().getAttribute(SUCCESS_MESSAGES_KEY);
        if (messages == null) {
            messages = new ArrayList<String>();
        }
        messages.add(msg);
        request.getSession().setAttribute(SUCCESS_MESSAGES_KEY, messages);
    }

    /**
     * Display a validation error
     * 
     * @param request
     */
    protected void validationErrorMessage(final HttpServletRequest request) {
        saveError(request, "There was a problem with the information you entered.");
    }

    /**
     * Redefine the default message
     * 
     * @param error the error field
     * @param message the new message
     */
    protected void redefineErrorMessage(final DefaultMessageSourceResolvable error, final String message) {
        try {
            final Field defaultMessage = DefaultMessageSourceResolvable.class.getDeclaredField("defaultMessage");
            defaultMessage.setAccessible(true);
            defaultMessage.set(error, message);
        } catch (final Exception e) {
            e.printStackTrace();
            // could not redefine message
        }
    }
}