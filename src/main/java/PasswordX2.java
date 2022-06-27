import java.util.Scanner;

class PasswordX2 {
    private final String p1, p2;

    public PasswordX2(Scanner scanner) {
        //TODO Hide the password so it is not printed to the CLI.
        System.out.print("Password: ");
        p1 = scanner.nextLine();

        System.out.print("Re-type password: ");
        p2 = scanner.nextLine();
    }

    public boolean isDifferent() {
        return !p1.equals(p2);
    }

    public String getPass() {
        return p1;
    }

}
