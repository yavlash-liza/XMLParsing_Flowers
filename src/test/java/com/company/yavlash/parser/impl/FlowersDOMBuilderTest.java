package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.Flower;
import com.company.yavlash.exception.XmlFlowerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class FlowersDOMBuilderTest extends BaseTest{
    private FlowersDOMBuilder domBuilder;

    public FlowersDOMBuilderTest() throws XmlFlowerException {}

    @Before
    public void init() throws XmlFlowerException {
        domBuilder = new FlowersDOMBuilder();
    }

    @Test
    public void getFlowersTest_FlowersDOMBuilder_RelevantData() throws XmlFlowerException {
        //given
        int expected = 3;

        //when
        domBuilder.buildFlowers(xmlPath);
        Set<Flower> flowers = domBuilder.getFlowers();

        //then
        Assert.assertEquals(expected, flowers.size());
        Assert.assertTrue(flowers.contains(flower));
    }
}