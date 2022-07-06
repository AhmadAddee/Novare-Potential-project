package bank.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

class Password {

    private final Integer salt;
    private final byte[] hash;


    public Password(String clearText) {
        Random rand = new Random();
        this.salt = rand.nextInt();
        this.hash = getSHA256(clearText, salt);
    }

    protected Password(String clearText, Integer salt){
        this.salt = salt;
        this.hash = getSHA256(clearText, salt);
    }

    /**
     * Generates a SHA256 hash of a clear text String, in this case a password.
     * @param clearPassword The clear text password we want to generate a hash from.
     * @param salt The salt which is to be used.
     * @return The password hash.
     */
    private static byte[] getSHA256(String clearPassword, Integer salt){

        String saltString = salt.toString();
        String msg = clearPassword + saltString;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Compares the stored password hash against a cleartext password.
     * @param clearText The password that we want to check against.
     * @return True if the passwords are the same, false if they are not.
     */
    public boolean compare(String clearText){
        byte[] other = getSHA256(clearText,this.salt);
        return Arrays.equals(other,this.hash);
    }

    protected Integer getSalt(){
        return this.salt;
    }

    protected byte[] getHash(){
        return this.hash;
    }
}
