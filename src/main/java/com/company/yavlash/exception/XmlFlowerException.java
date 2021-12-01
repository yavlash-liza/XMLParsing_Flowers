package com.company.yavlash.exception;

public class XmlFlowerException extends Exception{
    public XmlFlowerException() {
    }

    public XmlFlowerException(String message) {
        super(message);
    }

    public XmlFlowerException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlFlowerException(Throwable cause) {
        super(cause);
    }
}