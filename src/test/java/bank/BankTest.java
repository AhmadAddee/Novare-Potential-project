package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    @Test
    void signInCorrectUsernameAndPassword() {
        Bank b = new Bank();
        var s1 = b.signIn("adam","1234");
        assertTrue(s1.isPresent());
    }

    @Test
    void signInCorrectUsernameButWrongPassword(){
        Bank b = new Bank();
        var s2 = b.signIn("adam","12345");
        assertTrue(s2.isEmpty());
    }

    @Test
    void signInWrongUsernameAndPassword(){
        Bank b = new Bank();
        var s3 = b.signIn("adam123","1234");
        assertTrue(s3.isEmpty());
    }

    @Test
    void signUpUserAlreadyExisting() {
        Bank b = new Bank();
        boolean r = b.signUp("adam","Adam Howler","test");
        assertFalse(r);
    }

    @Test
    void signUpAndIn(){
        Bank b = new Bank();
        boolean r = b.signUp("eve","Eve Howler","test");
        assertTrue(r);

        var s = b.signIn("eve","test");
        assertTrue(s.isPresent());
    }
}