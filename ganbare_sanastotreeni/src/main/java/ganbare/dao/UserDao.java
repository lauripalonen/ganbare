package ganbare.dao;

import java.sql.*;

/**
 * Data access object which communicates with the user database.
 */
public class UserDao {

    private String address;

    /**
     * 
     * @param address address to the database. 
     */
    public UserDao(String address) {
        this.address = address;
    }

    /**
     * Adds new user to the User database
     * @param name username 
     * @param password user password
     * @throws SQLException Exception if unable to add new user
     */
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

    /**
     * Checks if user credentials are found from the database
     * @param name username
     * @param password user password
     * @return boolean value whether or not credentials are found
     * @throws SQLException exception if login fails
     */
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

    /**
     * Counts total number of users
     * @return total number of user
     * @throws SQLException 
     */
    public int userCount() throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM User");

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    /**
     * Formats the userTest database. After format the database has one initial
     * user for testing. 
     * @throws SQLException 
     */
    public void formatTestUsers() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./userTest", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE NOT name = 'doNotRemove';");

        stmt.executeUpdate();
    }

}
