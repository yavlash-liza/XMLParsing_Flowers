package com.company.yavlash.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class FlowerErrorHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();

    public FlowerErrorHandler() {
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.log(Level.WARN, getExceptionMessage(exception));
        throw new SAXException(getExceptionMessage(exception), exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.log(Level.ERROR, getExceptionMessage(exception));
        throw new SAXException(getExceptionMessage(exception), exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.log(Level.FATAL, getExceptionMessage(exception));
        throw new SAXException(getExceptionMessage(exception), exception);
    }

    private String getExceptionMessage(SAXParseException exception){
        return String.format("%d : %d - %s", exception.getLineNumber(), exception.getColumnNumber(), exception.getMessage());
    }
}