package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    public void password() {
        Password p1 = new Password("Secure Password");
        Password p2 = new Password("Secure Password"); // <--- Different salt = not the same

        assertNotNull(p1.hash);
        assertNotNull(p1.salt);

        assertNotNull(p2.hash);
        assertNotNull(p2.salt);

        assertNotEquals(p1.hash,p2.hash); // <--- Different salt = not the same hash

        Password p3 = new Password("Secure Password",p1.salt); // <--- same salt as p1 i.e should yeild the same hash
        assertNotNull(p3.hash);
        assertNotNull(p3.salt);

        assertArrayEquals(p3.hash,p1.hash);

        assertTrue(p1.compare("Secure Password"));
        assertTrue(p2.compare("Secure Password"));
        assertTrue(p3.compare("Secure Password"));
    }
}