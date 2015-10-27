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

import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Unit tests for the {@link OriginController} class
 */
public class OriginControllerTest extends AbstractControllerTest {

    private OriginController classUnderTest;

    @Mock
    private Origin mockOrigin;

    @Mock
    private NamedObjectManager<Origin> mockOriginManager;

    /**
     * Setup the tests
     */
    @Before
    public void setup() {
        classUnderTest = new OriginController();
        Whitebox.setInternalState(classUnderTest, "originManager", mockOriginManager);
    }

    /**
     * Test retrieving a list of origins
     */
    @Test
    public void testHandleRequest() throws Exception {
        when(mockOriginManager.getAll()).thenReturn(Arrays.asList(mockOrigin));

        ModelAndView mav = classUnderTest.handleRequest();
        Map<String, Object> m = mav.getModel();
        assertNotNull(m.get("origins"));
        assertEquals("admin/originList", mav.getViewName());
    }
}