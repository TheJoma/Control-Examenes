package com.proyecto.sistema.exceptions;

public class OperationUtils {
    public static String timBrackets(String message){
        return message.replaceAll("[\\[\\]]","");
    }
}
