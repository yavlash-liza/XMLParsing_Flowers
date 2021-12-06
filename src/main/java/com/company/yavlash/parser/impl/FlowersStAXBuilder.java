package com.company.yavlash.parser.impl;

import com.company.yavlash.parser.AbstractFlowersBuilder;
import com.company.yavlash.entity.*;
import com.company.yavlash.entity.enums.*;
import com.company.yavlash.exception.XmlFlowerException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.String.format;

public class FlowersStAXBuilder extends AbstractFlowersBuilder {
    private static final Logger logger = LogManager.getLogger();
    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";
    private final XMLInputFactory inputFactory;

    public FlowersStAXBuilder() {
        inputFactory = XMLInputFactory.newFactory();
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildFlowers(String fileName) throws XmlFlowerException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowerEnumTag.AQUATIC_FLOWER.toString().equals(name)
                            || FlowerEnumTag.GARDEN_FLOWER.toString().equals(name)
                            || FlowerEnumTag.WILD_FLOWER.toString().equals(name)) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new XmlFlowerException("Stax parsing error!", e);
        } catch (FileNotFoundException e) {
            throw new XmlFlowerException(format("File %s not found", fileName), e);
        } catch (IOException e) {
            throw new XmlFlowerException(format("Impossible close file %s ", fileName), e);
        }
        logger.log(Level.INFO, "Flowers were created successfully");
    }

    private Flower buildFlower(XMLStreamReader reader) throws XMLStreamException, XmlFlowerException {
        Flower flower;
        FlowerEnumTag flowerEnumTag = FlowerEnumTag.parseDepositXmlTag(reader.getLocalName());
        flower = switch (flowerEnumTag) {
            case AQUATIC_FLOWER -> new AquaticFlower();
            case GARDEN_FLOWER -> new GardenFlower();
            case WILD_FLOWER -> new WildFlower();
            default -> throw new XmlFlowerException("Invalid flower type");
        };
        String id = reader.getAttributeValue(null, FlowerEnumTag.FLOWER_ID.toString());
        flower.setFlowerId(id);
        String info = reader.getAttributeValue(null, FlowerEnumTag.FLOWER_INFO.toString());
        flower.setFlowerInfo(info);

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(DASH, UNDERSCORE);
                    setFlowerProperty(reader, name, flower);
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(DASH, UNDERSCORE);
                    if (FlowerEnumTag.valueOf(name) == FlowerEnumTag.AQUATIC_FLOWER
                            || FlowerEnumTag.valueOf(name) == FlowerEnumTag.GARDEN_FLOWER
                            || FlowerEnumTag.valueOf(name) == FlowerEnumTag.WILD_FLOWER) {
                        return flower;
                    }
                }

            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    private void setFlowerProperty(XMLStreamReader reader, String name, Flower flower) throws XMLStreamException {
        switch (FlowerEnumTag.valueOf(name)) {
            case FLOWER_NAME -> {
                String flowerName = getXmlText(reader);
                flower.setFlowerName(flowerName);
            }
            case SOIL -> {
                String soil = getXmlText(reader);
                flower.setSoilType(SoilType.valueOf(soil.toUpperCase(Locale.ROOT)));
            }
            case ORIGIN -> {
                String origin = getXmlText(reader);
                flower.setOrigin(origin);
            }
            case VISUAL_PARAMETERS -> settingVisualParameters(reader, flower);
            case GROWING_TIPS -> setGrowingTips(reader, flower);
            case MULTIPLYING -> {
                String multiplying = getXmlText(reader);
                flower.setMultiplying(Multiplying.valueOf(multiplying.toUpperCase(Locale.ROOT)));
            }
            case PLANTING_DATE -> {
                String date = getXmlText(reader);
                flower.setPlantingDate(LocalDate.parse(date));
            }
            case ORIGIN_TYPE -> {
                assert flower instanceof WildFlower;
                String originType = getXmlText(reader);
                ((WildFlower)flower).setOriginType(OriginType.valueOf(originType.toUpperCase(Locale.ROOT)));
            }
            case WATER_TYPE -> {
                assert flower instanceof AquaticFlower;
                String waterType = getXmlText(reader);
                ((AquaticFlower)flower).setWaterType(WaterType.valueOf(waterType.toUpperCase(Locale.ROOT)));
            }
            case FLOWER_AGE -> {
                assert flower instanceof GardenFlower;
                String flowerAge = getXmlText(reader);
                ((GardenFlower)flower).setFlowerAge(FlowerAge.valueOf(flowerAge.toUpperCase(Locale.ROOT)));
            }
        }

    }

    private void settingVisualParameters(XMLStreamReader reader, Flower flower) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(DASH, UNDERSCORE);
                    switch (FlowerEnumTag.valueOf(name)) {
                        case LEAVES_FORM -> {
                            String leavesForm = getXmlText(reader);
                            flower.getVisualParameters().setLeavesForm(leavesForm);
                        }
                        case ROOT -> {
                            String root = getXmlText(reader);
                            flower.getVisualParameters().setRoot(root);
                        }
                        case AVERAGE_HEIGHT -> {
                            String averageHeight = getXmlText(reader);
                            flower.getVisualParameters().setAverageHeight(Integer.parseInt(averageHeight));
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(DASH, UNDERSCORE);
                    if (FlowerEnumTag.valueOf(name) == FlowerEnumTag.VISUAL_PARAMETERS) {
                        return;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag VisualParameters");
    }

    private void setGrowingTips(XMLStreamReader reader, Flower flower) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT);
                    switch (FlowerEnumTag.valueOf(name)) {
                        case TEMPERATURE -> {
                            String temperature = getXmlText(reader);
                            flower.getGrowingTips().setTemperature(Integer.parseInt(temperature));
                        }
                        case LIGHT -> {
                            String light = getXmlText(reader);
                            flower.getGrowingTips().setLight(light);
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase(Locale.ROOT).replace(DASH, UNDERSCORE);
                    if (FlowerEnumTag.valueOf(name) == FlowerEnumTag.GROWING_TIPS) {
                        return;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag GrowingTips");
    }

    private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}