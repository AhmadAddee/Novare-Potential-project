package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank b = new Bank();

    @Test
    void signInCorrectUsernameAndPassword() {
        var s1 = b.signIn("adam","1234");
        assertTrue(s1.isPresent());
    }

    @Test
    void signInCorrectUsernameButWrongPassword(){
        var s2 = b.signIn("adam","12345");
        assertTrue(s2.isEmpty());
    }

    @Test
    void signInWrongUsernameAndPassword(){
        var s3 = b.signIn("adam123","1234");
        assertTrue(s3.isEmpty());
    }

    @Test
    void signUpUserAlreadyExisting() {
        boolean r = b.signUp("adam","Adam Howler","test");
        assertFalse(r);
    }

    @Test
    void signUpAndIn(){
        boolean r = b.signUp("eve","Eve Howler","test");
        assertTrue(r);

        var s = b.signIn("eve","test");
        assertTrue(s.isPresent());
    }

    public static Bank createNewTestBankInstance(){
        return new Bank();
    }
}