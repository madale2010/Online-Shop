package listeners;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import database.DBManager;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextAttributeListener, ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    	  ServletContext ctx = servletContextEvent.getServletContext();
          DBManager dbManager = (DBManager) ctx.getAttribute("DBManager");
          if(dbManager!=null){
        	  dbManager.closeConnection();
        	  System.out.println("Database connection closed for Application.");
          }
          // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
          Enumeration<Driver> drivers = DriverManager.getDrivers();
          while (drivers.hasMoreElements()) {
              Driver driver = drivers.nextElement();
              try {
                  DriverManager.deregisterDriver(driver);
                  ctx.log(String.format("deregistering jdbc driver: %s", driver));
              } catch (SQLException e) {
            	  ctx.log(String.format("Error deregistering driver %s", driver), e);
              }

          }

    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	
    	ServletContext ctx = servletContextEvent.getServletContext();
         
         String url = ctx.getInitParameter("DB_URL");
         String u = ctx.getInitParameter("DB_USER");
         String p = ctx.getInitParameter("DB_PWD");
          
         //create database connection from init parameters and set it to context
         DBManager dbManager = new DBManager(url, u, p);
         ctx.setAttribute("db", dbManager);
         //System.out.println("Database connection initialized for Application.");

    }
	
}
