package bank.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserDBTest {

    UserDB userDB = UserDB.createMockup();

    @BeforeEach
    private void init(){
        UserDB userDB = UserDB.createMockup();
    }



    @Test
    void test2Get() {
        userDB.createUser(
                "peter",
                "Peter Pan",
                "asd"
        );

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
        userDB.createUser(
                "peter",
                "Peter Pan",
                "asd"
        );
        assertTrue(userDB.containsUser("peter"));
    }

    @Test
    void test4GetNumberOfUsers() {

        assertEquals(3,userDB.getNumberOfUsers());
    }
}