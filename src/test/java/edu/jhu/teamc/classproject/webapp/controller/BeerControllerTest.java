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

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Unit tests for the {@link BeerController} class
 */
public class BeerControllerTest extends AbstractControllerTest {

    private BeerController classUnderTest;

    @Mock
    private Beer mockBeer;

    @Mock
    private NamedObjectManager<Beer> mockBeerManager;

    /**
     * Setup the tests
     */
    @Before
    public void setup() {
        classUnderTest = new BeerController();
        Whitebox.setInternalState(classUnderTest, "beerManager", mockBeerManager);
    }

    /**
     * Test retrieving a list of beers
     */
    @Test
    public void testHandleRequest() throws Exception {
        when(mockBeerManager.getAll()).thenReturn(Arrays.asList(mockBeer));

        ModelAndView mav = classUnderTest.handleRequest();
        Map<String, Object> m = mav.getModel();
        assertNotNull(m.get("beerList"));
        assertEquals("admin/beerList", mav.getViewName());
    }
}