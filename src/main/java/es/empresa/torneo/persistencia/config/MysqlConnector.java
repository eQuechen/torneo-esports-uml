package es.empresa.torneo.persistencia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {

    private static MysqlConnector instance;

    private String jdbcVersion;
    private Connection connection;

    private final String DB_NAME     = System.getenv("MYSQL_DB_NAME");
    private final String DB_HOST     = System.getenv("MYSQL_DB_HOST");
    private final String DB_USER     = System.getenv("MYSQL_DB_USER");
    private final String DB_PASSWORD = System.getenv("MYSQL_DB_PASSWORD");
    private final String DB_PORT     = System.getenv("MYSQL_DB_PORT");
    private final String DB_URL      = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private final String DB_DRIVER   = "com.mysql.cj.jdbc.Driver";

    private MysqlConnector() {

         try {
             this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             this.jdbcVersion = String.format("%s.%s",
                                            DriverManager.getDriver(DB_URL).getMajorVersion(),
                                            DriverManager.getDriver(DB_URL).getMinorVersion());

             System.out.printf("""
                                 %nConexión establecida con MySQL.
                                 Versión en ejecución de Java: %s
                                 Versión en ejecución de JDBC: %s
                                 """,
                                 System.getProperty("java.version"),
                                 this.jdbcVersion);

        } catch (SQLException e) {
             e.printStackTrace();
            System.out.println("Error conectando con MySQL");
        }
    }

    public static MysqlConnector getInstance() {
        if (instance == null)
            instance = new MysqlConnector();

        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public String getJdbcVersion() {
        return this.jdbcVersion;
    }
}
