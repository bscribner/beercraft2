package com.benscribner.beercraft.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.BeerImpl;
import com.benscribner.beercraft.service.BeerManager;
import com.benscribner.beercraft.service.NamedObjectManager;
import com.benscribner.beercraft.webapp.editor.IdentifiableEditor;

/**
 * Controller class for managing beer
 */
@Controller
@RequestMapping("/admin/beerform*")
public class BeerFormController extends BaseController {

    private static final String LIST_VIEW = "redirect:/admin/beerlist";
    private static final String FORM_VIEW = "/admin/beerform";

    @Resource(name = "beerManager")
    private BeerManager beerManager;

    @Resource(name = "categoryManager")
    private NamedObjectManager<Category> categoryManager;

    @Resource(name = "breweryManager")
    private NamedObjectManager<Brewery> breweryManager;

    @Resource(name = "originManager")
    private NamedObjectManager<Origin> originManager;

    /**
     * @see com.benscribner.beercraft.webapp.controller.BaseFormController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Category.class, new IdentifiableEditor<Category>(categoryManager));
        binder.registerCustomEditor(Brewery.class, new IdentifiableEditor<Brewery>(breweryManager));
        binder.registerCustomEditor(Origin.class, new IdentifiableEditor<Origin>(originManager));
    }

    /**
     * Provide an existing object to the form if the id param is present, a new
     * object otherwise
     * 
     * @param request
     *            the request object
     * @return a beer
     * @throws Exception
     */
    @ModelAttribute("beer")
    public Beer beer(final HttpServletRequest request) throws Exception {

        Beer beer;
        final String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            beer = new BeerImpl();
        } else {
            beer = beerManager.get(id);
        }

        return beer;
    }

    /**
     * Retrieve all the categories
     * 
     * @return a list of all the categories
     */
    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryManager.getAll();
    }

    /**
     * Retrieve all the breweries
     * 
     * @return a list of all the breweries
     */
    @ModelAttribute("breweries")
    public List<Brewery> breweries() {
        return breweryManager.getAll();
    }

    /**
     * Retrieve all the origins
     * 
     * @return a list of all the origins
     */
    @ModelAttribute("origins")
    public List<Origin> origins() {
        return originManager.getAll();
    }

    /**
     * Display the beer form
     */
    @RequestMapping(method = RequestMethod.GET)
    public String beerForm() {
        return FORM_VIEW;
    }

    /**
     * Delete the beer
     * 
     * @param beer
     *            the beer to delete
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
    public String delete(@ModelAttribute("beer") final Beer beer, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        try {
            beerManager.remove(beer.getId());

            saveMessage(request, "The beer has been removed");

        } catch (final Exception e) {
            saveError(request, "The beer could not be removed. It is being referenced by and order or shopping cart.");

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }

    /**
     * Handle the form submission
     * 
     * @param beer
     *            the beer object bound to the form
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("beer") final Beer beer, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        log.debug("entering 'onSubmit' method...");

        if (request.getParameter("cancel") != null) {
            return LIST_VIEW;
        }

        if (errors.hasErrors()) {
            validationErrorMessage(request);

            // fix validation of price
            final ObjectError priceError = errors.getFieldError("price");
            if (priceError != null && priceError.getDefaultMessage().contains("NumberFormatException")) {
                redefineErrorMessage(priceError, "Enter a valid price");
            }

            // fix validation of abv
            final ObjectError abvError = errors.getFieldError("alcoholContent");
            if (abvError != null && abvError.getDefaultMessage().contains("NumberFormatException")) {
                redefineErrorMessage(abvError, "Enter a valid ABV");
            }

            return FORM_VIEW;
        }

        try {
            beerManager.save(beer);

            saveMessage(request, "The beer has been saved.");
        } catch (final Exception e) {
            validationErrorMessage(request);

            errors.rejectValue("name", "errors.existing.name", "Name must be unique");

            ((BeerImpl) beer).setId(null);

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }
}