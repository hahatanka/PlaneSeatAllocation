package repository;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DBManager {
        PropertiesConfiguration databaseProperties = new PropertiesConfiguration();

        private static Connection connection;

        public DBManager(){
            try {
                databaseProperties.load("database.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }

            this.setUpDB();
        }

        public static Connection getConnection() {
            return connection;
        }

        private void setUpDB() {

            String userName = databaseProperties.getString("database.userName");
            String password = databaseProperties.getString("database.password");
            String host = databaseProperties.getString("database.host");
            String port = databaseProperties.getString("database.port");
            String dbName = databaseProperties.getString("database.dbName");
            String connectionUrl = host + ":" + port + "/" + dbName;

            try {
                connection = DriverManager.getConnection(connectionUrl, userName, password);
            } catch (SQLException ex) {
                System.out.println("Unable to connect to database");
                ex.printStackTrace();
            }
        }


    }


