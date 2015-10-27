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

import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * Unit tests for the {@link CategoryController} class
 */
public class CategoryControllerTest extends AbstractControllerTest {

    private CategoryController classUnderTest;

    @Mock
    private Category mockCategory;

    @Mock
    private NamedObjectManager<Category> mockCategoryManager;

    /**
     * Setup the tests
     */
    @Before
    public void setup() {
        classUnderTest = new CategoryController();
        Whitebox.setInternalState(classUnderTest, "categoryManager", mockCategoryManager);
    }

    /**
     * Test retrieving a list of categories
     */
    @Test
    public void testHandleRequest() throws Exception {
        when(mockCategoryManager.getAll()).thenReturn(Arrays.asList(mockCategory));

        ModelAndView mav = classUnderTest.handleRequest();
        Map<String, Object> m = mav.getModel();
        assertNotNull(m.get("categories"));
        assertEquals("admin/categoryList", mav.getViewName());
    }
}