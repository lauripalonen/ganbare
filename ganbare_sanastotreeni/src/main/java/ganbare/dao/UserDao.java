package ganbare.dao;

import java.sql.*;
import java.util.*;
import ganbare.domain.SqlParameters;

public class UserDao {

    public void newUser(String name, String password) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./user", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(name, password)"
                + "VALUES(?, ?)");

        stmt.setString(1, name);
        stmt.setString(2, password);

        stmt.executeUpdate();

        stmt.close();
        connection.close();

    }

    public boolean loginUser(String name, String password) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./user", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT name FROM User WHERE name = ? AND password = ?");

        stmt.setString(1, name);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return true;
        }

        rs.close();
        stmt.close();
        connection.close();

        return false;

    }

    public void testConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./user", "sa", "");
        System.out.println("Connection established to: " + connection.getCatalog());
        System.out.println("URL: " + connection.getMetaData());
        System.out.println("");

        PreparedStatement stmt = connection.prepareStatement("SHOW TABLES");

        ResultSet rs = stmt.executeQuery();

        System.out.println("AVAILABLE TABLES: ");
        while (rs.next()) {

            System.out.println(rs.getString(1));
        }

        rs.close();
        stmt.close();
        connection.close();

    }

}
