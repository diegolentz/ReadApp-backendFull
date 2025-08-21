package ar.com.mandarina.readapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String msj) {
        super(msj); 
    }
}