package dao;

import dao.employee.EmployeeDAO;
import dao.employee.EmployeeDAOImpl;
import dao.parser.DOMParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleFactory extends DBFactory {
    private static Connection connection = null;

    public static Connection connect() {
        if (connection == null) {
            try {
                DOMParser.setConnectionProperties();
                connection = DriverManager.getConnection(DOMParser.DBUrl, DOMParser.user, DOMParser.password);
                Class.forName(DOMParser.DBDriver);

                if (!connection.isClosed()) {
                    System.out.println("Connected successfully!");
                }
            } catch (SQLException | ClassNotFoundException | ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOImpl();
    }
}
