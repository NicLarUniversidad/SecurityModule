package com.cl.evaluation.register.expections;

public class AuthenticationException extends Exception {
    public static String USER_NOT_FOUND = "Ha ocurrido un error, no se encontró al usuario, pero el token es válido";

    public AuthenticationException(String message) {
        super(message);
    }
}
