package com.dub.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

public class SessionListener
        implements HttpSessionListener, HttpSessionIdListener,
        ServletContextListener
{
    private static final Logger log = LogManager.getLogger();

    @Inject SessionRegistry sessionRegistry;

    @Override
    public void sessionCreated(HttpSessionEvent e)
    {
   	 	log.info("Session created.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e)
    {
    	 log.info("Session destroyed.");
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent e, String oldSessionId)
    {
    	 log.info("SessionId changed.");
    }

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(
                        event.getServletContext());
        AutowireCapableBeanFactory factory =
                context.getAutowireCapableBeanFactory();
        factory.autowireBeanProperties(this,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        factory.initializeBean(this, "sessionListener");
        log.info("Session listener initialized in Spring application context.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { 
    	log.info("Session listener destroyed in Spring application context.");
    	
    }
}