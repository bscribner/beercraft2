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

import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.service.UserManager;

/**
 * Unit tests for the {@link UserController} class
 */
public class UserControllerTest extends AbstractControllerTest {

    private UserController classUnderTest;
    
    @Mock
    private User mockUser;
    
    @Mock
    private UserManager<User> mockUserManager;
    
    /**
     * Setup the tests
     */
    @Before
    public void setup() {
        classUnderTest = new UserController();
        Whitebox.setInternalState(classUnderTest, "userManager", mockUserManager);
    }
    

    /**
     * Test retrieving a list of users
     */
    @Test
    public void testHandleRequest() throws Exception {
        when(mockUserManager.getAll()).thenReturn(Arrays.asList(mockUser));
        
        ModelAndView mav = classUnderTest.handleRequest();
        Map<String, Object> m = mav.getModel();
        assertNotNull(m.get("users"));
        assertEquals("admin/userList", mav.getViewName());
    }
}