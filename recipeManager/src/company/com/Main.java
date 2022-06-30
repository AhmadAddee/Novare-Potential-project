package company.com;

import company.com.userView.Person;


public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.printBanner();
        p.sessionLoop();
    }
}
