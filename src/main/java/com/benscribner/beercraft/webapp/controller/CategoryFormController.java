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

import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.impl.CategoryImpl;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Controller class for managing a Category
 */
@Controller
@RequestMapping("/admin/categoryform*")
public class CategoryFormController extends BaseController {

    private static final String LIST_VIEW = "redirect:/admin/categories";
    private static final String FORM_VIEW = "/admin/categoryform";

    @Resource(name = "categoryManager")
    private NamedObjectManager<Category> categoryManager;

    /**
     * Provide an existing object to the form if the id param is present, a new
     * object otherwise
     * 
     * @param request
     *            the request object
     * @return a category
     * @throws Exception
     */
    @ModelAttribute("category")
    public Category category(final HttpServletRequest request) throws Exception {

        Category category;
        final String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            category = new CategoryImpl();
        } else {
            category = categoryManager.get(id);
        }

        return category;
    }

    /**
     * Display the category form
     */
    @RequestMapping(method = RequestMethod.GET)
    public String categoryForm() {
        return FORM_VIEW;
    }

    /**
     * Delete the category
     * 
     * @param category
     *            the category to delete
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
    public String delete(@ModelAttribute("category") final Category category, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        try {
            categoryManager.remove(category.getId());

            saveMessage(request, "The category has been removed.");

        } catch (final Exception e) {
            saveError(request, "The category could not be removed. It is being referenced by at least one Beer.");

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }

    /**
     * Handle the form submission
     * 
     * @param category
     *            the category object bound to the form
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("category") final Category category, final BindingResult errors,
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
            categoryManager.save(category);

            saveMessage(request, "The category has been saved.");
        } catch (final Exception e) {
            validationErrorMessage(request);

            errors.rejectValue("name", "errors.existing.name", "Name must be unique");

            ((CategoryImpl) category).setId(null);

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }
}