package com.company.yavlash.builder;

import com.company.yavlash.entity.Flower;
import com.company.yavlash.exception.XmlFlowerException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowersBuilder {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    protected Set<Flower> flowers;

    public AbstractFlowersBuilder() {
        flowers = new HashSet<>();
    }

    public AbstractFlowersBuilder(Set<Flower> flowers) {
        this.flowers = flowers;
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public abstract void buildSetFlowers(String filename) throws XmlFlowerException;

//    protected LocalDate parseStringToLocalDate(String data) {  //нужен ли вообще?
//        DateTimeFormatter dateTimeFormatter;
//        LocalDate date;
//
//        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
//        date = LocalDate.parse(data, dateTimeFormatter);
//        return date;
//    }
}