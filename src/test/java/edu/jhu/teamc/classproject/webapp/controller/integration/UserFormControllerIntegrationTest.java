package com.benscribner.beercraft.webapp.controller.integration;

import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.benscribner.beercraft.Constants;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.webapp.controller.UserFormController;

/**
 * Integration test for the {@link UserFormController}
 */
@Ignore
public class UserFormControllerIntegrationTest extends AbstractControllerIntegrationTest {

    @Autowired
    private final UserFormController c = null;
    private MockHttpServletRequest request;
    private User user;

    @Test
    public void testAdd() throws Exception {
        log.debug("testing add new user...");
        request = newGet("/userform.html");
        request.addParameter("from", "list");
        request.addParameter("method", "Add");
        request.addUserRole(Constants.ADMIN_ROLE);

        user = c.user(request);
        assertNull(user.getUsername());
    }

    // @Test
    // public void testAddWithoutPermission() throws Exception {
    // log.debug("testing add new user...");
    // request = newGet("/userform.html");
    // request.addParameter("method", "Add");
    //
    // try {
    // c.showForm(request, new MockHttpServletResponse());
    // fail("AccessDeniedException not thrown...");
    // } catch (AccessDeniedException ade) {
    // assertNotNull(ade.getMessage());
    // }
    // }
    //
    // @Test
    // public void testCancel() throws Exception {
    // log.debug("testing cancel...");
    // request = newPost("/userform.html");
    // request.addParameter("cancel", "");
    //
    // BindingResult errors = new DataBinder(user).getBindingResult();
    // String view = c.onSubmit(user, errors, request, new
    // MockHttpServletResponse());
    //
    // assertEquals("redirect:/mainMenu", view);
    // }
    //
    // @Test
    // public void testEdit() throws Exception {
    // log.debug("testing edit...");
    // request = newGet("/userform.html");
    // request.addParameter("id", "-1"); // regular user
    // request.addUserRole(Constants.ADMIN_ROLE);
    //
    // User user = c.showForm(request, new MockHttpServletResponse());
    // assertEquals("Tomcat User", user.getFullName());
    // }
    //
    // @Test
    // public void testEditWithoutPermission() throws Exception {
    // log.debug("testing edit...");
    // request = newGet("/userform.html");
    // request.addParameter("id", "-1"); // regular user
    //
    // try {
    // c.showForm(request, new MockHttpServletResponse());
    // fail("AccessDeniedException not thrown...");
    // } catch (AccessDeniedException ade) {
    // assertNotNull(ade.getMessage());
    // }
    // }
    //
    // @Test
    // public void testEditProfile() throws Exception {
    // log.debug("testing edit profile...");
    // request = newGet("/userform.html");
    // request.setRemoteUser("user");
    //
    // user = c.showForm(request, new MockHttpServletResponse());
    // assertEquals("Tomcat User", user.getFullName());
    // }
    //
    // @Test
    // public void testSave() throws Exception {
    // request = newPost("/userform.html");
    // // set updated properties first since adding them later will
    // // result in multiple parameters with the same name getting sent
    // User user = ((UserManager)
    // applicationContext.getBean("userManager")).getUser("-1");
    // user.setConfirmPassword(user.getPassword());
    // user.setLastName("Updated Last Name");
    //
    // request.setRemoteUser(user.getUsername());
    //
    // BindingResult errors = new DataBinder(user).getBindingResult();
    // c.onSubmit(user, errors, request, new MockHttpServletResponse());
    //
    // assertFalse(errors.hasErrors());
    // assertNotNull(request.getSession().getAttribute("successMessages"));
    // }
    //
    // @Test
    // public void testAddWithMissingFields() throws Exception {
    // request = newPost("/userform.html");
    // user = new User();
    // user.setFirstName("Jack");
    // request.setRemoteUser("user");
    //
    // BindingResult errors = new DataBinder(user).getBindingResult();
    // c.onSubmit(user, errors, request, new MockHttpServletResponse());
    //
    // assertTrue(errors.getAllErrors().size() == 6);
    // }
    //
    // @Test
    // public void testRemove() throws Exception {
    // request = newPost("/userform.html");
    // request.addParameter("delete", "");
    // user = new User();
    // user.setId(-2L);
    //
    // BindingResult errors = new DataBinder(user).getBindingResult();
    // c.onSubmit(user, errors, request, new MockHttpServletResponse());
    //
    // assertNotNull(request.getSession().getAttribute("successMessages"));
    // }
}
