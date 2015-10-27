package com.benscribner.beercraft.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.validation.BindingResult;

import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.PersonImpl;
import com.benscribner.beercraft.model.impl.UserImpl;
import com.benscribner.beercraft.service.UserManager;

/**
 * Test setup for the controller unit tests
 */
@RunWith(PowerMockRunner.class)
public abstract class AbstractControllerTest {

    protected transient final Log log = LogFactory.getLog(getClass());

    @Mock
    protected HttpServletRequest mockRequest;

    @Mock
    protected HttpServletResponse mockResponse;

    @Mock
    protected HttpSession mockSession;

    @Mock
    protected BindingResult mockBindingResult;

    @Mock
    protected UserImpl mockUser;

    @Mock
    protected PersonImpl mockPerson;

    @Mock
    protected UserManager<User> mockUserManager;
}