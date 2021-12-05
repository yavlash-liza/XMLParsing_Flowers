package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.Flower;
import com.company.yavlash.exception.XmlFlowerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class FlowersSAXBuilderTest extends BaseTest{
    private FlowersSAXBuilder saxBuilder;

    public FlowersSAXBuilderTest() throws XmlFlowerException {}

    @Before
    public void init() throws XmlFlowerException {
        saxBuilder = new FlowersSAXBuilder();
    }

    @Test
    public void getFlowersTest_FlowersSAXBuilder_RelevantData(){
        //given
        int expected = 3;

        //when
        saxBuilder.buildFlowers(xmlPath, xsdPath);
        Set<Flower> flowers = saxBuilder.getFlowers();

        //then
        Assert.assertEquals(expected, flowers.size());
    }
}