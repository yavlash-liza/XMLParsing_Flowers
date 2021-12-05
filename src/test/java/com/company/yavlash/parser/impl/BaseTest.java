package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.GrowingTips;
import com.company.yavlash.entity.VisualParameters;
import com.company.yavlash.entity.WildFlower;
import com.company.yavlash.entity.enums.Multiplying;
import com.company.yavlash.entity.enums.OriginType;
import com.company.yavlash.entity.enums.SoilType;
import com.company.yavlash.exception.XmlFlowerException;

import java.time.LocalDate;

public abstract class BaseTest {
    protected String xmlPath;
    protected String xsdPath;
    protected WildFlower flower;

    public BaseTest() throws XmlFlowerException {
        xmlPath = getClass().getResource("/test_flowers.xml").getFile();
        xsdPath = getClass().getResource("/test_flowers.xsd").getFile();

        flower = new WildFlower();
        flower.setFlowerId("i094756");
        flower.setFlowerInfo("оранжевый цветок");
        flower.setFlowerName("Achillea millefolium");
        flower.setSoilType(SoilType.SANDY);
        flower.setOrigin("Europe");

        VisualParameters visualParameters = new VisualParameters();
        visualParameters.setLeavesForm("Toothed");
        visualParameters.setRoot("Fibrous");
        visualParameters.setAverageHeight(15);

        flower.setVisualParameters(visualParameters);

        GrowingTips growingTips = new GrowingTips();
        growingTips.setTemperature(10);
        growingTips.setLight("Sun");

        flower.setGrowingTips(growingTips);
        flower.setMultiplying(Multiplying.CUTTINGS);
        flower.setPlantingDate(LocalDate.of(2018, 9, 15));
        flower.setOriginType(OriginType.FIELD);
    }
}
