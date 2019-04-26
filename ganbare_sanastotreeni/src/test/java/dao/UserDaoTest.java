package dao;

import ganbare.dao.UserDao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao ud;

    public UserDaoTest() {
    }

    @Before
    public void setUp() {
        ud = new UserDao("jdbc:h2:./userTest");
    }

    @After
    public void tearDown() {

        try {
            ud.formatTestUsers();
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
    }

    @Test
    public void newUserAddsNewUser() {
        int oldCount = 0;

        try {
            oldCount = ud.userCount();
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }

        try {
            ud.newUser("test", "test");
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }

        int newCount = 0;

        try {
            newCount = ud.userCount();
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }

        assertTrue((newCount - oldCount) == 1);

    }

    @Test
    public void formatTestUsersRemovesAllButOne() {
        try {
            ud.formatTestUsers();
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }

        int count = -1;

        try {
            count = ud.userCount();
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }

        assertEquals(1, count);

    }

    @Test
    public void loginUserReturnsTrueWhenCorrectUser() {
        boolean loggedIn = false;

        try {
            loggedIn = ud.loginUser("doNotRemove", "doNotRemove");
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }

        assertTrue(loggedIn);

    }

    @Test
    public void loginUserReturnsFalseWhenIncorrectUser() {
        boolean loggedIn = true;

        try {
            loggedIn = ud.loginUser("iDoNotExist", "iDoNotExist");
        } catch (Exception e) {
            System.out.println("Virhe: " + e);

        }

        assertFalse(loggedIn);
    }

}
