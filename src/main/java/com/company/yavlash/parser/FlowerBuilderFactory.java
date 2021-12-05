package com.company.yavlash.parser;

import com.company.yavlash.parser.impl.FlowersDOMBuilder;
import com.company.yavlash.parser.impl.FlowersStAXBuilder;
import com.company.yavlash.entity.enums.ParserType;
import com.company.yavlash.exception.XmlFlowerException;

public class FlowerBuilderFactory {
    public static AbstractFlowersBuilder createFlowerBuilder(String typeParser) throws XmlFlowerException {
        ParserType type = ParserType.valueOf(typeParser.toUpperCase());
        switch (type) {
            case STAX:
                return new FlowersStAXBuilder();
            case DOM:
                return new FlowersDOMBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}