package bank.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void withdraw() {
        User u1 = new User("adamA","adam hunter","1234");
        int originalBalance = u1.balance;
        u1.withdraw(100);

        assertNotEquals(u1.balance,originalBalance);
        assertEquals(u1.balance, originalBalance - 100);
    }

    @Test
    void deposit() {
        User u1 = new User("adamA","adam hunter","1234");
        int originalBalance = u1.balance;
        u1.deposit(100);

        assertNotEquals(u1.balance,originalBalance);
        assertEquals(u1.balance, originalBalance + 100);
    }

    @Test
    void transfer() {
        User u1 = new User("adamA","adam hunter","1234");
        User u2 = new User("adamB","adam fletcher","1235");

        int originalBalance1 = u1.balance;
        int originalBalance2 = u2.balance;

        u1.transfer(100,u2);

        assertNotEquals(u1.balance,originalBalance1);
        assertNotEquals(u2.balance,originalBalance2);

        assertEquals(u1.balance, originalBalance1 - 100);
        assertEquals(u2.balance, originalBalance2 + 100);
    }

    @Test
    void validUsername() {
        String validUsername = "adam";
        String invalidUsername = "adam1234";
        String invalidUsername2 = "adam adam adam";
        String invalidUsername3 = "";
        String invalidUsername4 = " ";
        String invalidUsername5 = "     ";
        String invalidUsername6 = "\n";
        assertTrue(User.validUsername(validUsername));
        assertFalse(User.validUsername(invalidUsername));
        assertFalse(User.validUsername(invalidUsername2));
        assertFalse(User.validUsername(invalidUsername3));
        assertFalse(User.validUsername(invalidUsername4));
        assertFalse(User.validUsername(invalidUsername5));
        assertFalse(User.validUsername(invalidUsername6));
    }
}