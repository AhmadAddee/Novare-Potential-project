package bank.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class Password {

    private final Integer salt;
    private final byte[] hash;

    public Password(String clearText) {
        Random rand = new Random();
        this.salt = rand.nextInt();
        this.hash = getSHA256(clearText,salt);
    }

    public Password(String clearText, Integer salt){
        this.salt = salt;
        this.hash = getSHA256(clearText, salt);
    }

    private byte[] getSHA256(String clearPassword, Integer salt){

        String saltString = this.salt.toString();
        String msg = clearPassword + saltString;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

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
