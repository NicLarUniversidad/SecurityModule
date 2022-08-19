package com.cl.evaluation.register.expections;

public class InvalidFormatException extends Exception {
    public static String VOID_MAIL = "El campo de email está vacío";
    public static String INVALID_MAIL_FORMAT = "El formato del email es inválido";
    public static String VOID_PASSWORD = "La contraseña está vacía";
    public static String INVALID_PASSWORD_FORMAT = "El formato de la contraseña es inválido";
    public InvalidFormatException(String message) {
        super(message);
    }
}
