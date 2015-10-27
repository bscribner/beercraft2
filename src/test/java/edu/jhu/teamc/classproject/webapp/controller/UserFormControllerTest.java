package com.benscribner.beercraft.webapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.benscribner.beercraft.model.User;

/**
 * Unit tests for the {@link UserFormController}
 */
public class UserFormControllerTest extends AbstractControllerTest {

    private UserFormController classUnderTest;

    /**
     * Setup the tests
     */
    @Before
    public void setup() {
        classUnderTest = new UserFormController();

        Whitebox.setInternalState(classUnderTest, "userManager", mockUserManager);

        when(mockRequest.getSession()).thenReturn(mockSession);
    }

    /**
     * Test display the form view for a new user
     */
    @Test
    public void testNewUserFormView() throws Exception {
        when(mockRequest.getParameter(eq("id"))).thenReturn("");

        final User user = classUnderTest.user(mockRequest);

        assertNull(user.getUsername());
    }

    /**
     * Test display the form view for an existing user
     */
    @Test
    public void testExistingUserFormView() throws Exception {
        final String id = UUID.randomUUID().toString();

        when(mockRequest.getParameter("from")).thenReturn("list");
        when(mockRequest.getParameter(eq("id"))).thenReturn(id.toString());

        when(mockUserManager.get(eq(id))).thenReturn(mockUser);

        final User user = classUnderTest.user(mockRequest);

        // mockUser and user should be the same object
        assertTrue(mockUser == user);
    }

    /**
     * Test the cancel view
     */
    @Test
    public void testCancel() throws Exception {
        when(mockRequest.getParameter(eq("cancel"))).thenReturn("");

        final String view = classUnderTest.onSubmit(mockUser, mockBindingResult, mockRequest, mockResponse);

        assertEquals("redirect:/admin/users", view);
    }

    /**
     * Test save the form
     * 
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        final String id = UUID.randomUUID().toString();

        when(mockRequest.getParameter(eq("id"))).thenReturn(id.toString());

        when(mockUserManager.get(eq(id))).thenReturn(mockUser);
        when(mockUser.getPerson()).thenReturn(mockPerson);

        when(mockUser.getPassword()).thenReturn("somepassword");
        when(mockUser.getConfirmPassword()).thenReturn("somepassword");

        when(mockRequest.getParameter(eq("cancel"))).thenReturn(null);

        final String view = classUnderTest.onSubmit(mockUser, mockBindingResult, mockRequest, mockResponse);

        assertEquals("redirect:/admin/users", view);
    }

    /**
     * Test delete the object
     * 
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception {
        final String view = classUnderTest.delete(mockUser, mockBindingResult, mockRequest, mockResponse);

        assertEquals("redirect:/admin/users", view);
    }
}
