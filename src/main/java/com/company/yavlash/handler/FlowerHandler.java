package com.company.yavlash.handler;

import com.company.yavlash.entity.*;
import com.company.yavlash.entity.enums.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private Set<Flower> flowerSet;
    private Flower flower;
    private FlowerEnumTag flowerEnumTag;
    private EnumSet<FlowerEnumTag> withText;

    public FlowerHandler() {
        flowerSet = new HashSet<>();
        withText = EnumSet.range(FlowerEnumTag.FLOWER_NAME, FlowerEnumTag.FLOWER_AGE);
    }

    public Set<Flower> getFlowerSet(){
        return flowerSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (FlowerEnumTag.AQUATIC_FLOWER.toString().equals(qName) || FlowerEnumTag.GARDEN_FLOWER.toString().equals(qName)
                || FlowerEnumTag.WILD_FLOWER.toString().equals(qName) || FlowerEnumTag.VISUAL_PARAMETERS.toString().equals(qName)
                || FlowerEnumTag.GROWING_TIPS.toString().equals(qName) || FlowerEnumTag.MULTIPLYING.toString().equals(qName)
                || FlowerEnumTag.FLOWER_AGE.toString().equals(qName) || FlowerEnumTag.WATER_TYPE.toString().equals(qName)
                || FlowerEnumTag.ORIGIN_TYPE.toString().equals(qName)
        ){
            flowerEnumTag = FlowerEnumTag.parseDepositXmlTag(qName);
            switch (flowerEnumTag){
                case AQUATIC_FLOWER -> flower = new AquaticFlower();
                case GARDEN_FLOWER -> flower = new GardenFlower();
                case WILD_FLOWER -> flower = new WildFlower();
            }
            flowerEnumTag = null;
            flower.setFlowerId(attributes.getValue(FlowerEnumTag.FLOWER_ID.toString()));
            flower.setFlowerInfo(attributes.getValue(FlowerEnumTag.FLOWER_INFO.toString()));
        } else {
            FlowerEnumTag temp = FlowerEnumTag.parseDepositXmlTag(qName);
            if (withText.contains(temp)){
                flowerEnumTag = temp;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (flowerEnumTag != null){
            switch (flowerEnumTag){
                case FLOWER_NAME -> flower.setFlowerName(data.toUpperCase(Locale.ROOT));
                case SOIL -> flower.setSoilType(SoilType.valueOf(data.toUpperCase(Locale.ROOT)));
                case ORIGIN -> flower.setOrigin(data.toUpperCase(Locale.ROOT));
                case LEAVES_FORM -> flower.getVisualParameters().setLeavesForm(data.toUpperCase(Locale.ROOT));
                case ROOT -> flower.getVisualParameters().setRoot(data.toUpperCase(Locale.ROOT));
                case AVERAGE_HEIGHT -> flower.getVisualParameters().setAverageHeight(Integer.parseInt(data));
                case TEMPERATURE -> flower.getGrowingTips().setTemperature(Integer.parseInt(data));
                case LIGHT -> flower.getGrowingTips().setLight(data.toUpperCase(Locale.ROOT));
                case MULTIPLYING -> flower.setMultiplying(Multiplying.valueOf(data));
                case PLANTING_DATE -> flower.setPlantingDate(LocalDate.parse(data));
                case ORIGIN_TYPE -> ((WildFlower)flower).setOriginType(OriginType.valueOf(data));
                case WATER_TYPE -> ((AquaticFlower)flower).setWaterType(WaterType.valueOf(data));
                case FLOWER_AGE -> ((GardenFlower)flower).setFlowerAge(FlowerAge.valueOf(data));
                default -> throw new EnumConstantNotPresentException(
                        flowerEnumTag.getDeclaringClass(), flowerEnumTag.name());
            }
        }
        flowerEnumTag = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (FlowerEnumTag.AQUATIC_FLOWER.toString().equals(qName) || FlowerEnumTag.GARDEN_FLOWER.toString().equals(qName) || FlowerEnumTag.WILD_FLOWER.toString().equals(qName)){
            flowerSet.add(flower);
        }
    }
}