package com.benscribner.beercraft.webapp.controller.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.benscribner.beercraft.webapp.controller.UserController;

/**
 * Integration tests for the {@link UserController} class
 */
@Ignore
public class UserControllerIntegrationTest extends AbstractControllerIntegrationTest {

    @Autowired
    private UserController c;

    /**
     * Test retrieving a list of users
     */
    @Test
    public void testHandleRequest() throws Exception {
        ModelAndView mav = c.handleRequest();
        Map<String, Object> m = mav.getModel();
        assertNotNull(m.get("users"));
        assertEquals("admin/userList", mav.getViewName());
    }
}