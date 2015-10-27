package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

import com.benscribner.beercraft.model.Role;

/**
 * Unit tests for the {@link UserImpl} class
 */
public class UserImplTest {

    private UserImpl classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new UserImpl();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    /**
     * Test the constructor which takes a username
     */
    @Test
    public void testConstructor() {
        classUnderTest = new UserImpl("username");
        assertEquals("username", classUnderTest.getUsername());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        classUnderTest.setUsername("username");
        classUnderTest.setPassword("password");
        classUnderTest.setConfirmPassword("password");
        classUnderTest.setEnabled(true);

        final RoleImpl roleA = new RoleImpl("roleA");
        final RoleImpl roleB = new RoleImpl("roleB");

        final List<Role> roles = new ArrayList<Role>();
        roles.add(roleA);
        classUnderTest.setRoles(roles);
        classUnderTest.addRole(roleB);

        final PersonImpl person = new PersonImpl("first", "last");
        classUnderTest.setPerson(person);

        assertEquals("username", classUnderTest.getUsername());
        assertEquals("password", classUnderTest.getPassword());
        assertEquals("password", classUnderTest.getConfirmPassword());

        assertTrue(classUnderTest.getRoles().contains(roleA));
        assertTrue(classUnderTest.getRoles().contains(roleB));

        assertTrue(classUnderTest.getAuthorities().contains(roleA));
        assertTrue(classUnderTest.getAuthorities().contains(roleB));

        assertTrue(classUnderTest.isEnabled());
        assertTrue(classUnderTest.isAccountNonExpired());
        assertTrue(classUnderTest.isAccountNonLocked());
        assertTrue(classUnderTest.isCredentialsNonExpired());

        assertEquals(person, classUnderTest.getPerson());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final UserImpl a = new UserImpl("username");
        final UserImpl b = new UserImpl("username");
        final UserImpl c = new UserImpl("different username");
        final UserImpl d = new UserImpl("username") {
        };

        new EqualsTester(a, b, c, d);
    }
}
