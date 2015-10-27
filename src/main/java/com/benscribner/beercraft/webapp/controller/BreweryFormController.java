package com.benscribner.beercraft.webapp.controller;

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

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.impl.BreweryImpl;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Controller class for managing a Brewery
 */
@Controller
@RequestMapping("/admin/breweryform*")
public class BreweryFormController extends BaseController {

    private static final String LIST_VIEW = "redirect:/admin/breweries";
    private static final String FORM_VIEW = "/admin/breweryform";

    @Resource(name = "breweryManager")
    private NamedObjectManager<Brewery> breweryManager;

    /**
     * Provide an existing object to the form if the id param is present, a new
     * object otherwise
     * 
     * @param request
     *            the request object
     * @return a brewery
     * @throws Exception
     */
    @ModelAttribute("brewery")
    public Brewery brewery(final HttpServletRequest request) throws Exception {

        Brewery brewery;
        final String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            brewery = new BreweryImpl();
        } else {
            brewery = breweryManager.get(id);
        }

        return brewery;
    }

    /**
     * Display the brewery form
     */
    @RequestMapping(method = RequestMethod.GET)
    public String breweryForm() {
        return FORM_VIEW;
    }

    /**
     * Delete the brewery
     * 
     * @param brewery
     *            the brewery to delete
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the return view
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, params = "delete")
    public String delete(@ModelAttribute("brewery") final Brewery brewery, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        try {
            breweryManager.remove(brewery.getId());

            saveMessage(request, "The brewery has been removed.");

        } catch (final Exception e) {
            saveError(request, "The brewery could not be removed. It is being referenced by at least one Beer.");

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }

    /**
     * Handle the form submission
     * 
     * @param brewery
     *            the brewery object bound to the form
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("brewery") final Brewery brewery, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        log.debug("entering 'onSubmit' method...");

        if (request.getParameter("cancel") != null) {
            return LIST_VIEW;
        }

        if (errors.hasErrors()) {
            validationErrorMessage(request);
            
            return FORM_VIEW;
        }

        try {
            breweryManager.save(brewery);

            saveMessage(request, "The brewery has been saved.");
        } catch (final Exception e) {
            validationErrorMessage(request);
            
            errors.rejectValue("name", "errors.existing.name", "Name must be unique");
            
            ((BreweryImpl) brewery).setId(null);
            
            return FORM_VIEW;
        }

        return LIST_VIEW;
    }
}