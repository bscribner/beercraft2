package com.benscribner.beercraft.webapp.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.benscribner.beercraft.Constants;
import com.benscribner.beercraft.model.Role;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.UserImpl;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Controller class for managing a user account
 */
@Controller
@RequestMapping("/account*")
public class AccountFormController extends BaseController {

    private static final String COMPLETION_VIEW = "redirect:/main";
    private static final String FORM_VIEW = "accountform";

    @Resource(name = "roleManager")
    private NamedObjectManager<Role> roleManager;

    @Resource(name = "authenticationManager")
    protected AuthenticationManager authenticationManager;

    /**
     * Provide an existing user to the form if the username is present, a new
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

        final Principal principal = request.getUserPrincipal();

        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            user = (UserImpl) userManager.loadUserByUsername(principal.getName());
        } else {
            user = new UserImpl();
            user.addRole(roleManager.getByName(Constants.USER_ROLE));
        }

        ((UserImpl) user).setConfirmPassword(user.getPassword());

        return user;

    }

    /**
     * Display the account form
     */
    @RequestMapping(method = RequestMethod.GET)
    public String accountForm() {

        return FORM_VIEW;

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
            return COMPLETION_VIEW;
        }
        
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "errors.confirmPassword", "Passwords must match");
        }

        if (errors.hasErrors()) {
            validationErrorMessage(request);
            
            return FORM_VIEW;
        }

        try {
            if (StringUtils.isBlank(user.getId())) {
                // copy original password as reference because the usermanager will
                // hash the password
                final String originalPassword = new String(user.getPassword());
                
                userManager.save(user);

                authenticateUserAndSetSession(user, originalPassword, request);
            } else {
                userManager.saveExistingUser(user);
            }

            saveMessage(request, "Your account information has been saved.");
        } catch (final Exception e) {
            errors.rejectValue("username", "errors.existing.user", "Username and E-mail must be unique");

            // redisplay the unencrypted passwords
            ((UserImpl) user).setPassword(user.getConfirmPassword());
            ((UserImpl) user).setId(null);
            
            validationErrorMessage(request);

            return FORM_VIEW;
        }

        return COMPLETION_VIEW;
    }

    /**
     * Authenticate the user
     * 
     * @param user the user to authenticate
     * @param password the user's password
     * @param request the request
     * 
     * Concept obtained here: http://stackoverflow.com/questions/3813028/auto-login-after-successful-registeration
     */
    private void authenticateUserAndSetSession(final User user, final String password, final HttpServletRequest request) {

        // generate session if one doesn't exist
        request.getSession();

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
                password);

        token.setDetails(new WebAuthenticationDetails(request));
        final Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}