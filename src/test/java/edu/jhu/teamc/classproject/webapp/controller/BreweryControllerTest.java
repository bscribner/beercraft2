package com.benscribner.beercraft.webapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Unit tests for the {@link BreweryController} class
 */
public class BreweryControllerTest extends AbstractControllerTest {

    private BreweryController classUnderTest;

    @Mock
    private Brewery mockBrewery;

    @Mock
    private NamedObjectManager<Brewery> mockBreweryManager;

    /**
     * Setup the tests
     */
    @Before
    public void setup() {
        classUnderTest = new BreweryController();
        Whitebox.setInternalState(classUnderTest, "breweryManager", mockBreweryManager);
    }

    /**
     * Test retrieving a list of breweries
     */
    @Test
    public void testHandleRequest() throws Exception {
        when(mockBreweryManager.getAll()).thenReturn(Arrays.asList(mockBrewery));

        ModelAndView mav = classUnderTest.handleRequest();
        Map<String, Object> m = mav.getModel();
        assertNotNull(m.get("breweries"));
        assertEquals("admin/breweryList", mav.getViewName());
    }
}