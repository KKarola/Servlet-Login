package appLayer;

public class User {
    public boolean isValidUserCredential(String userName, String userPassword) {
        if (userName.equals("Karolina") && userPassword.equals("test123"))
            return true;
        return false;
    }
}