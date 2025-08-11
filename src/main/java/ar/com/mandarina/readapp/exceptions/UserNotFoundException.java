package ar.com.mandarina.readapp.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("No se encontró el usuario: " + username);
    }
}