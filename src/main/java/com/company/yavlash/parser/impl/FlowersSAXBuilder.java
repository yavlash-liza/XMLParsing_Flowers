package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.Flower;
import com.company.yavlash.handler.FlowerHandler;
import com.company.yavlash.validator.FlowerXmlValidator;
import com.company.yavlash.validator.impl.FlowerXmlValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FlowersSAXBuilder {
    private static final Logger logger = LogManager.getLogger();
    private Set<Flower> flowers;
    private FlowerHandler handler;
    private XMLReader reader;
    private FlowerXmlValidator validator = new FlowerXmlValidatorImpl();

    public FlowersSAXBuilder() {
        flowers = new HashSet<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        handler = new FlowerHandler();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser cannot be created which satisfies the requested configuration");
        } catch (SAXException e) {
            logger.log(Level.ERROR, "SAX error occur during processing: %s", e);
        }
        reader.setContentHandler(handler);
    }

    public void buildFlowers(String xmlFilePath, String xsdFilePath) {
        try { reader.parse(xmlFilePath);
        } catch (IOException e) {
            logger.log(Level.ERROR, "IO exception occur during parsing {}", e);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "SAX exception occur during parsing {}", e);
        }
        flowers = handler.getFlowerSet();
    }

    public Set<Flower> getFlowers() {
        return Set.copyOf(flowers);
    }
}