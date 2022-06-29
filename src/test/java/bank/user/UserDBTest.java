package bank.user;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)    // <-- Will make the tests run in order
class UserDBTest {

    private  final UserDB userDB = UserDB.getInstance();

    @Test
    void test1CreateUser() {
        userDB.createUser(
                "peter",
                "Peter Pan",
                "asd"
        );
    }

    @Test
    void test2Get() {
        var user = userDB.get("peter");
        var noneUser = userDB.get("Albin");

        assertTrue(user.isPresent());
        assertTrue(noneUser.isEmpty());

        assertEquals("peter",user.get().getUsername());
        assertEquals("Peter Pan", user.get().getFullName());
        assertTrue(user.get().checkPassword("asd"));
    }

    @Test
    void test3ContainsUser() {
        assertTrue(userDB.containsUser("peter"));
    }

    @Test
    void test4GetNumberOfUsers() {
        assertEquals(4,userDB.getNumberOfUsers());
    }
}