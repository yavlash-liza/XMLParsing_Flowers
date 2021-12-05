package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.Flower;
import com.company.yavlash.exception.XmlFlowerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class FlowersStAXBuilderTest extends BaseTest{
    private FlowersStAXBuilder stAXBuilder;

    public FlowersStAXBuilderTest() throws XmlFlowerException {}

    @Before
    public void init(){
        stAXBuilder = new FlowersStAXBuilder();
    }

    @Test
    public void getFlowersTest_FlowersStAXBuilder_RelevantData() throws XmlFlowerException {
        //given
        int expected = 3;

        //when
        stAXBuilder.buildFlowers(xmlPath);
        Set<Flower> flowers = stAXBuilder.getFlowers();

        //then
        Assert.assertEquals(expected, flowers.size());
        Assert.assertTrue(flowers.contains(flower));
    }
}