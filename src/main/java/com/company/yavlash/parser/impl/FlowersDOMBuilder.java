package com.company.yavlash.parser.impl;

import com.company.yavlash.parser.AbstractFlowersBuilder;
import com.company.yavlash.entity.*;
import com.company.yavlash.entity.enums.*;
import com.company.yavlash.exception.XmlFlowerException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import static java.lang.String.format;

public class FlowersDOMBuilder extends AbstractFlowersBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final DocumentBuilder documentBuilder;

    public FlowersDOMBuilder() throws XmlFlowerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "DocumentBuilder can't be created", e);
            throw new XmlFlowerException("DocumentBuilder can't be created", e);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildFlowers(String fileName) throws XmlFlowerException {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            createSpecificTypeFlowers(root, FlowerEnumTag.AQUATIC_FLOWER);
            createSpecificTypeFlowers(root, FlowerEnumTag.WILD_FLOWER);
            createSpecificTypeFlowers(root, FlowerEnumTag.GARDEN_FLOWER);
        } catch (SAXException e) {
            throw new XmlFlowerException(format("File %s can not be parsed", fileName), e);
        } catch (IOException e) {
            throw new XmlFlowerException(format("File %s can not be read", fileName), e);
        }
    }

    private void createSpecificTypeFlowers(Element root, FlowerEnumTag flowerType) throws XmlFlowerException {
        NodeList flowerNodeList = root.getElementsByTagName(flowerType.toString());
        for (int i = 0; i < flowerNodeList.getLength(); i++) {
            Element flowerElement = (Element) flowerNodeList.item(i);
            Flower flower = buildFlowers(flowerElement, flowerType);
            flowers.add(flower);
        }
    }

    private Flower buildFlowers(Element flowerElement, FlowerEnumTag flowerType) throws XmlFlowerException {
        Flower flower;
        switch (flowerType) {
            case AQUATIC_FLOWER -> {
                flower = new AquaticFlower();
                ((AquaticFlower)flower).setWaterType(
                        WaterType.valueOf(getElementTextContent(flowerElement, FlowerEnumTag.WATER_TYPE.toString()).toUpperCase(Locale.ROOT)));
            }
            case GARDEN_FLOWER -> {
                flower = new GardenFlower();
                ((GardenFlower)flower).setFlowerAge(
                        FlowerAge.valueOf(getElementTextContent(flowerElement, FlowerEnumTag.FLOWER_AGE.toString()).toUpperCase(Locale.ROOT)));
            }
            case WILD_FLOWER -> {
                flower = new WildFlower();
                ((WildFlower)flower).setOriginType(
                        OriginType.valueOf(getElementTextContent(flowerElement, FlowerEnumTag.ORIGIN_TYPE.toString()).toUpperCase(Locale.ROOT)));
            }
            default -> throw new XmlFlowerException("Invalid plant type");
        }
        String info = flowerElement.getAttribute(FlowerEnumTag.FLOWER_INFO.toString());
        flower.setFlowerInfo(info);

        flower.setFlowerId(flowerElement.getAttribute(FlowerEnumTag.FLOWER_ID.toString()));

        String name = getElementTextContent(flowerElement, FlowerEnumTag.FLOWER_NAME.toString());
        flower.setFlowerName(name);

        String soil = getElementTextContent(flowerElement, FlowerEnumTag.SOIL.toString());
        flower.setSoilType(SoilType.valueOf(soil.toUpperCase(Locale.ROOT)));

        String origin = getElementTextContent(flowerElement, FlowerEnumTag.ORIGIN.toString());
        flower.setOrigin(origin);

        settingVisualParameters(flowerElement, flower);

        settingGrowingTips(flowerElement, flower);

        String multiplying = getElementTextContent(flowerElement, FlowerEnumTag.MULTIPLYING.toString());
        flower.setMultiplying(Multiplying.valueOf(multiplying.toUpperCase(Locale.ROOT)));

        String date = getElementTextContent(flowerElement, FlowerEnumTag.PLANTING_DATE.toString());
        flower.setPlantingDate(LocalDate.parse(date));
        return flower;
    }

    private String getElementTextContent(Element element, String elementTagName) {
        NodeList nodeList = element.getElementsByTagName(elementTagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private void settingVisualParameters(Element flowerElement, Flower flower){
        NodeList visualParamsList = flowerElement.getElementsByTagName(FlowerEnumTag.VISUAL_PARAMETERS.toString());
        Element visualParameters = (Element) visualParamsList.item(0);

        String leavesForm = getElementTextContent(visualParameters, FlowerEnumTag.LEAVES_FORM.toString());
        flower.getVisualParameters().setLeavesForm(leavesForm);

        String root = getElementTextContent(visualParameters, FlowerEnumTag.ROOT.toString());
        flower.getVisualParameters().setRoot(root);

        String averageHeight = getElementTextContent(visualParameters, FlowerEnumTag.AVERAGE_HEIGHT.toString());
        flower.getVisualParameters().setAverageHeight(Integer.parseInt(averageHeight));
    }

    private void settingGrowingTips(Element flowerElement, Flower flower) {
        NodeList growingTipsList = flowerElement.getElementsByTagName(FlowerEnumTag.GROWING_TIPS.toString());
        Element growingTips = (Element) growingTipsList.item(0);

        String temperature = getElementTextContent(growingTips, FlowerEnumTag.TEMPERATURE.toString());
        flower.getGrowingTips().setTemperature(Integer.parseInt(temperature));

        String light = getElementTextContent(growingTips, FlowerEnumTag.LIGHT.toString());
        flower.getGrowingTips().setLight(light);
    }
}