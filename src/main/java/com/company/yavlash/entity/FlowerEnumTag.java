package com.company.yavlash.entity;

public enum FlowerEnumTag {
    PROJECT,
    FLOWERS,
    AQUATIC_FLOWER,
    GARDEN_FLOWER,
    WILD_FLOWER,
    FLOWER_ID,
    FLOWER_INFO,
    FLOWER_NAME,
    SOIL,
    ORIGIN,
    VISUAL_PARAMETERS,
    LEAVES_FORM,
    ROOT,
    AVERAGE_HEIGHT,
    GROWING_TIPS,
    TEMPERATURE,
    LIGHT,
    MULTIPLYING,
    PLANTING_DATE,
    ORIGIN_TYPE,
    WATER_TYPE,
    FLOWER_AGE;

    public static final char DASH = '-';
    public static final char UNDERSCORE = '_';

    @Override
    public String toString(){
        return name().toLowerCase().replace(UNDERSCORE, DASH);
    }

    public static FlowerEnumTag parseDepositXmlTag(String text){
        return FlowerEnumTag.valueOf(text.toUpperCase().replace(DASH, UNDERSCORE));
    }
}