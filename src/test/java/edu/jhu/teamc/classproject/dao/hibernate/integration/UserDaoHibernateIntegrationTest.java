package com.benscribner.beercraft.dao.hibernate.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.benscribner.beercraft.Constants;
import com.benscribner.beercraft.dao.hibernate.RoleDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.UserDaoHibernate;
import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.PersonImpl;
import com.benscribner.beercraft.model.impl.RoleImpl;
import com.benscribner.beercraft.model.impl.UserImpl;

/**
 * Unit tests for the {@link UserDaoHibernate} class
 */
public class UserDaoHibernateIntegrationTest extends AbstractHibernateIntegrationTest<User> {

    @Autowired
    private UserDaoHibernate userDao;

    @Autowired
    private RoleDaoHibernate roleDao;

    /**
     * Setup the test
     */
    @Before
    public void setup() {
        roleDao.save(new RoleImpl(Constants.USER_ROLE));
        roleDao.save(new RoleImpl(Constants.ADMIN_ROLE));
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#getDao()
     */
    @Override
    protected UserDaoHibernate getDao() {
        return userDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected User createObject() {
        final UserImpl user = new UserImpl("username");
        user.setPassword("password");
        user.setConfirmPassword("confirmpassword");
        user.setEnabled(true);

        final PersonImpl person = new PersonImpl("firstname", "lastname");
        person.setEmail("email@email.com");
        person.setPhoneNumber("1234567890");

        final Address address = new Address();
        address.setAddress1("address line 1");
        address.setAddress2("address line 2");
        address.setAddress3("address line 3");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setPostalCode("12345");

        person.setAddress(address);

        user.setPerson(person);

        user.addRole(roleDao.getByName(Constants.ADMIN_ROLE));

        return user;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(User user) {
        UserImpl userImpl = (UserImpl) user;
        userImpl.setUsername("new username");
        userImpl.setPassword("new password");
        userImpl.setConfirmPassword("new confirmpassword");
        userImpl.setEnabled(true);

        final PersonImpl person = userImpl.getPerson();
        person.setFirstName("new firstname");
        person.setLastName("new lastname");
        person.setEmail("newemail@email.com");
        person.setPhoneNumber("0987654321");

        final Address address = person.getAddress();
        address.setAddress1("new address line 1");
        address.setAddress2("new address line 2");
        address.setAddress3("new address line 3");
        address.setCity("new city");
        address.setState("new state");
        address.setCountry("new country");
        address.setPostalCode("54321");

        user.addRole(roleDao.getByName(Constants.USER_ROLE));
    }

    /**
     * Test retrieving an invalid username
     * 
     * @throws Exception
     */
    @Test(expected = UsernameNotFoundException.class)
    public void testGetByUsernameInvalid() throws Exception {
        final UserDetails user = getDao().loadUserByUsername("invalid");
        assertNull(user);
    }

    /**
     * Test retrieving an object by name
     * 
     * @throws Exception
     */
    @Test
    public void testGetByUsername() throws Exception {
        final User user = createObject();
        getDao().save(user);
        flush();

        final UserDetails userDetails = getDao().loadUserByUsername(user.getUsername());
        assertNotNull(userDetails);
    }
}
