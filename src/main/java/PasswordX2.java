import java.util.Optional;
import java.util.Scanner;

/**
 * Reads in a password twice, like a typical password selection for registration.
 * Example:
 *
 * Please enter a password
 *  password:   asd
 *  re-type password:   asd
 */
class PasswordX2 {
    private final String p1, p2;

    /**
     * Reads in a password twice, like a typical password selection for registration
     */
    public PasswordX2() {
        Scanner scanner = new Scanner(System.in);
        //TODO Hide the password so it is not printed to the CLI.
        System.out.print("Password: ");
        p1 = scanner.nextLine();

        System.out.print("Re-type password: ");
        p2 = scanner.nextLine();
    }

    public boolean isDifferent() {
        return !p1.equals(p2);
    }

    /**
     * Get the password.
     * @return Returns an optional of a string that contains the password. Returns empty if the two passwords which the user entered
     * was different.
     */
    public Optional<String> getPass() {
        return isDifferent()?Optional.empty():Optional.of(p1);
    }

}
