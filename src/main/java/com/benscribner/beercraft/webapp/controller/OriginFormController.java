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

import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.OriginImpl;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Controller class for managing a Origin
 */
@Controller
@RequestMapping("/admin/originform*")
public class OriginFormController extends BaseController {

    private static final String LIST_VIEW = "redirect:/admin/origins";
    private static final String FORM_VIEW = "/admin/originform";

    @Resource(name = "originManager")
    private NamedObjectManager<Origin> originManager;

    /**
     * Provide an existing object to the form if the id param is present, a new
     * object otherwise
     * 
     * @param request
     *            the request object
     * @return a origin
     * @throws Exception
     */
    @ModelAttribute("origin")
    public Origin origin(final HttpServletRequest request) throws Exception {

        Origin origin;
        final String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            origin = new OriginImpl();
        } else {
            origin = originManager.get(id);
        }

        return origin;
    }

    /**
     * Display the origin form
     */
    @RequestMapping(method = RequestMethod.GET)
    public String originForm() {
        return FORM_VIEW;
    }

    /**
     * Delete the origin
     * 
     * @param origin
     *            the origin to delete
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
    public String delete(@ModelAttribute("origin") final Origin origin, final BindingResult errors,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        try {
            originManager.remove(origin.getId());

            saveMessage(request, "The origin has been removed.");

        } catch (final Exception e) {
            saveError(request, "The origin could not be removed. It is being referenced by at least one Beer.");

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }

    /**
     * Handle the form submission
     * 
     * @param origin
     *            the origin object bound to the form
     * @param errors
     *            any errors encountered
     * @param request
     *            the request object
     * @param response
     *            the response object
     * @return the appropriate view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid @ModelAttribute("origin") final Origin origin, final BindingResult errors,
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
            originManager.save(origin);

            saveMessage(request, "The origin has been saved.");
        } catch (final Exception e) {
            validationErrorMessage(request);

            errors.rejectValue("name", "errors.existing.name", "Name must be unique");

            ((OriginImpl) origin).setId(null);

            return FORM_VIEW;
        }

        return LIST_VIEW;
    }
}