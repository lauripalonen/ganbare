package ganbare.dao;

import java.sql.*;

public class UserDao {

    private String address;

    public UserDao(String address) {
        this.address = address;
    }

    public void newUser(String name, String password) throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(name, password)"
                + "VALUES(?, ?)");

        stmt.setString(1, name);
        stmt.setString(2, password);

        stmt.executeUpdate();

        stmt.close();
        connection.close();

    }

    public boolean loginUser(String name, String password) throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

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

    public int userCount() throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM User");

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void formatTestUsers() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./userTest", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE NOT name = 'doNotRemove';");

        stmt.executeUpdate();
    }

}
