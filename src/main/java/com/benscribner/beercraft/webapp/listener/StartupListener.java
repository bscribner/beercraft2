package com.benscribner.beercraft.webapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener class for initializing the roles if they do not exist in the database
 */
public class StartupListener implements ServletContextListener {

    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent event) {
        // set the derby home so that the database will be created in the right place
        System.setProperty("derby.system.home", event.getServletContext().getRealPath("/"));
    }

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        // Does nothing
    }
}