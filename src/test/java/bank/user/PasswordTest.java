package bank.user;

import bank.user.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    public void password() {
        Password p1 = new Password("Secure Password");
        Password p2 = new Password("Secure Password"); // <--- Different salt = not the same

        assertNotNull(p1.getHash());
        assertNotNull(p1.getSalt());

        assertNotNull(p2.getHash());
        assertNotNull(p2.getSalt());

        assertNotEquals(p1.getHash(),p2.getHash()); // <--- Different salt = not the same hash

        Password p3 = new Password("Secure Password",p1.getSalt()); // <--- same salt as p1 i.e should yeild the same hash
        assertNotNull(p3.getHash());
        assertNotNull(p3.getSalt());

        assertArrayEquals(p3.getHash(),p1.getHash());

        assertTrue(p1.compare("Secure Password"));
        assertTrue(p2.compare("Secure Password"));
        assertTrue(p3.compare("Secure Password"));
    }
}