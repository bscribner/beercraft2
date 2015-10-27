package com.benscribner.beercraft.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.benscribner.beercraft.Constants;
import com.benscribner.beercraft.model.Role;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.RoleImpl;
import com.benscribner.beercraft.model.impl.UserImpl;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Controller class for managing a user
 */
@Controller
@RequestMapping("/admin/user*")
public class UserFormController extends BaseController {

    private static final String VIEW = "redirect:/admin/users";
    private static final String FORM_VIEW = "/admin/userform";

    @Resource(name = "roleManager")
    private NamedObjectManager<Role> roleManager;

    /**
     * Provide an existing user to the form if the id param is present, a new
     * user otherwise
     * 
     * @param request
     *            the request object
     * @return a user
     * @throws Exception
     */
    @ModelAttribute("user")
    public User user(final HttpServletRequest request) throws Exception {

        User user;

        final String userId = request.getParameter("id");
        if (StringUtils.isBlank(userId)) {
            user = new UserImpl();
            user.addRole(new RoleImpl(Constants.USER_ROLE));
        } else {
            user = userManager.get(userId);
        }

        ((UserImpl) user).setConfirmPassword(user.getPassword());

        return user;

    }

    /**
     * Display the user form
     */
    @RequestMapping(method = RequestMethod.GET)
    public String userForm() {
        return FORM_VIEW;
    }

    /**
     * Retrieve all the roles
     * 
     * @return a list of all the roles
     */
    @ModelAttribute("roles")
    public List<Role> roles() {
        return roleManager.getAll();
    }

    /**
     * Delete the user
     * 
     * @param user
     *            the user to delete
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, params = "delete")
    public String delete(@ModelAttribute("user") final User user, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        try {
            userManager.remove(user.getId());

            saveMessage(request, "The user has been removed.");

        } catch (final Exception e) {
            saveError(request, "The user could not be removed. It is being referenced by an order or shopping cart.");

            return FORM_VIEW;
        }

        return VIEW;
    }

    /**
     * Handle the form submission
     * 
     * @param user
     *            the user object bound to the form
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("user") final User user, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        log.debug("entering 'onSubmit' method...");

        if (request.getParameter("cancel") != null) {
            return VIEW;
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "errors.confirmPassword", "Passwords must match");
        }

        if (errors.hasErrors()) {
            validationErrorMessage(request);

            return FORM_VIEW;
        }

        // add roles
        final String[] userRoles = request.getParameterValues("userRoles");
        user.getRoles().clear();
        if (userRoles != null) {
            for (final String roleId : userRoles) {
                user.addRole(roleManager.get(roleId));
            }
        }

        try {
            if (StringUtils.isBlank(user.getId())) {
                userManager.save(user);
            } else {
                userManager.saveExistingUser(user);
            }

            saveMessage(request, "The user has been saved.");
        } catch (final Exception e) {
            errors.rejectValue("username", "errors.existing.user", "Username and E-mail must be unique");

            // redisplay the unencrypted passwords
            ((UserImpl) user).setPassword(user.getConfirmPassword());
            ((UserImpl) user).setId(null);

            validationErrorMessage(request);

            return FORM_VIEW;
        }

        return VIEW;
    }
}