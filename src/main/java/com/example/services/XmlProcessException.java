package com.example.services;

public class XmlProcessException extends Exception {
    private String message;

    public XmlProcessException(String message) {
        this.message = message;
    }

    public String GetMessage() {
        return message;
    }
}
