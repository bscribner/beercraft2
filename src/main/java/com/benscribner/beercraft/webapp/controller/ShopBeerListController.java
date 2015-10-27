package com.benscribner.beercraft.webapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.service.BeerManager;
import com.benscribner.beercraft.service.NamedObjectManager;
import com.benscribner.beercraft.webapp.editor.IdentifiableEditor;

/**
 * List Beer by origin, category, or brewery
 */
@Controller
@RequestMapping("/beerlist*")
public class ShopBeerListController {

    private static final String LIST_VIEW = "beerList";

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
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Category.class, new IdentifiableEditor<Category>(categoryManager));
        binder.registerCustomEditor(Brewery.class, new IdentifiableEditor<Brewery>(breweryManager));
        binder.registerCustomEditor(Origin.class, new IdentifiableEditor<Origin>(originManager));
    }

    /**
     * List Beer by Category
     */
    @RequestMapping(method = RequestMethod.GET, params = "category")
    public ModelAndView listByCategory(@RequestParam("category") final Category category) throws Exception {
        return new ModelAndView(LIST_VIEW, "beerList", beerManager.getBeerByCategory(category));
    }

    /**
     * List Beer by Origin
     */
    @RequestMapping(method = RequestMethod.GET, params = "origin")
    public ModelAndView listByOrigin(@RequestParam("origin") final Origin origin) throws Exception {
        return new ModelAndView(LIST_VIEW, "beerList", beerManager.getBeerByOrigin(origin));
    }

    /**
     * List Beer by Brewery
     */
    @RequestMapping(method = RequestMethod.GET, params = "brewery")
    public ModelAndView listByBrewery(@RequestParam("brewery") final Brewery brewery) throws Exception {
        return new ModelAndView(LIST_VIEW, "beerList", beerManager.getBeerByBrewery(brewery));
    }
}