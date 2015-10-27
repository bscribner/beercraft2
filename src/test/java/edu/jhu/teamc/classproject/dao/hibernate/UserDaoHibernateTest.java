package com.benscribner.beercraft.dao.hibernate;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.UserImpl;

/**
 * Unit tests for the {@link UserDaoHibernate} class
 */
public class UserDaoHibernateTest extends GenericIdentifiableDaoHibernateTest<User, UserDaoHibernate> {

    @Mock
    private UserImpl mockUser;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<UserImpl> getPersistentClass() {
        return UserImpl.class;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected UserDaoHibernate getDao() {
        final UserDaoHibernate dao = new UserDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * Test retrieving an invalid username
     * 
     * @throws Exception
     */
    @Test(expected = UsernameNotFoundException.class)
    public void testGetByNameInvalid() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.uniqueResult()).thenReturn(null);

        assertNull(getDao().loadUserByUsername("invalid"));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(String.class));
        verify(mockQuery).uniqueResult();
    }

    /**
     * Test retrieving a username
     * 
     * @throws Exception
     */
    @Test
    public void testGetByName() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.uniqueResult()).thenReturn(mockUser);

        getDao().loadUserByUsername("valid");

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(String.class));
        verify(mockQuery).uniqueResult();
    }

    /**
     * Test the save method
     */
    @Test
    @Override
    public void testSaveExisting() {
        when(mockUser.getId()).thenReturn(UUID.randomUUID().toString());

        getDao().save(mockUser);

        verify(mockSession).merge(eq(mockUser));
        verify(mockSession).flush();
    }

    /**
     * Test the save method
     */
    @Test
    @Override
    public void testSaveNew() {
        when(mockUser.getId()).thenReturn(null);

        getDao().save(mockUser);

        verify(mockSession).persist(eq(mockUser));
        verify(mockSession).flush();
    }
}
