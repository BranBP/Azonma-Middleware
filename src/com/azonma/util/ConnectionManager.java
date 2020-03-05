package com.azonma.util;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;

public class ConnectionManager	 {

	private static Logger logger = LogManager.getLogger(ConnectionManager.class.getName());

	private static ResourceBundle dbConfiguration = ResourceBundle.getBundle("DBConfiguration"); 

	private static final String DRIVER_CLASS_NAME_PARAMETER = "jdbc.driver.classname";
	private static final String URL_PARAMETER = "jdbc.url";
	private static final String USER_PARAMETER = "jdbc.user";
	private static final String PASSWORD_PARAMETER = "jdbc.password";

	private static String url;
	private static String user;
	private static String password;

	private static ComboPooledDataSource dataSource = null;

	static {

		try {

			String driverClassName = dbConfiguration.getString(DRIVER_CLASS_NAME_PARAMETER);
			url = dbConfiguration.getString(URL_PARAMETER);
			user = dbConfiguration.getString(USER_PARAMETER);
			password = dbConfiguration.getString(PASSWORD_PARAMETER);

			// Carga el driver directamente, sin pool 
			// Class.forName(driverClassName);

			// Pool (Aunque la clase se apellide DataSource no es un java.sql.DataSource)
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(driverClassName); //loads the jdbc driver            
			dataSource.setJdbcUrl(url);
			dataSource.setUser(user);                                  
			dataSource.setPassword(password);  


		} catch (Exception e) {
			logger.fatal(e.getMessage(), e); 
		}

	}

	private ConnectionManager() {}

	public final static Connection getConnection() throws SQLException {
		//return DriverManager.getConnection(url, user, password);
		return (Connection) dataSource.getConnection();
	}

}