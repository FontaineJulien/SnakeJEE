package com.julfon.config;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.julfon.dao.DAOConfigurationException;
import com.julfon.dao.DAOFactory;

/*
 * Instanciation de la DAO factory au lancement de l'application.
 * Elle est placée en attribut dans le ServletContext pour être accessible
 * depuis les Servlets
 */
public class InitialisationDAOFactory implements ServletContextListener {
	
	private DAOFactory daofactory;
	
	private static final String ATT_DAO_FACTORY = "daofactory";

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		
		try {
			this.daofactory = DAOFactory.getInstance();
		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}
		
		servletContext.setAttribute(ATT_DAO_FACTORY, daofactory);
	}

}
