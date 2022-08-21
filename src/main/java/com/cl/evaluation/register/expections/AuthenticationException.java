package com.cl.evaluation.register.expections;

public class AuthenticationException extends Exception {
    public static final String NEW_TOKEN_HAS_BEEN_GENERATED = "Ya se ha generado un nuevo token, por lo que el token que está usando es inválido";
    public static final String USER_NOT_FOUND = "Ha ocurrido un error, no se encontró al usuario, pero el token es válido";

    public AuthenticationException(String message) {
        super(message);
    }
}
