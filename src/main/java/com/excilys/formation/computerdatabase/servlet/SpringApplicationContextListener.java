package com.excilys.formation.computerdatabase.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        sce.getServletContext().setAttribute("applicationContext", ac);            
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
   
}