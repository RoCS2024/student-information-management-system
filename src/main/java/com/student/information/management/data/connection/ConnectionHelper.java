package com.student.information.management.data.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The ConnectionHelper class connects to an Oracle database.
 */
public class ConnectionHelper {
    /** The connection URL. */
    public static String URL = "jdbc:oracle:thin:@localhost:1521:oracledb";
    /** The Oracle driver. */
    public static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    /** The username used to connect to the database. */
    public static String username = "system";
    /** The password used to connect to the database. */
    public static String password = "Changeme0";
    private static Logger LOGGER = LoggerFactory.getLogger(ConnectionHelper.class);

    /**
     * This method gets the connection from an Oracle database instance.
     * */
    public static Connection getConnection() throws RuntimeException {
        try {
            Class.forName(ORACLE_DRIVER).newInstance();
            return DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Error has occurred. Driver not found." + ex.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
            LOGGER.error("Error has occurred. Cannot create a database instance." + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error("Error has occurred. Cannot connect to the database." + ex.getMessage());
        }
        return null;
    }
}
