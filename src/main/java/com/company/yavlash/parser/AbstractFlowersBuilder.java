package com.company.yavlash.parser;

import com.company.yavlash.entity.Flower;
import com.company.yavlash.exception.XmlFlowerException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowersBuilder {
    protected Set<Flower> flowers;

    public AbstractFlowersBuilder() {
        flowers = new HashSet<>();
    }

    public AbstractFlowersBuilder(Set<Flower> flowers) {
        this.flowers = flowers;
    }

    public Set<Flower> getFlowers() {
        return Set.copyOf(flowers);
    }

    public abstract void buildFlowers(String filename) throws XmlFlowerException;
}