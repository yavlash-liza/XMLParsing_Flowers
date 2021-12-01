package com.company.yavlash.entity;

import com.company.yavlash.entity.enums.Light;

public class GrowingTips {
    private int temperature;
    private Light light;

    public GrowingTips() {
    }

    public GrowingTips(int temperature, Light light) {
        this.temperature = temperature;
        this.light = light;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        GrowingTips aThat = (GrowingTips) object;

        if(getLight() == null) {
            if(aThat.getLight() != null){return false;}
        } else if(!getLight().equals(aThat.getLight())){return false;}
        return getTemperature() == aThat.getTemperature();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getLight() == null ? 0 : getLight().hashCode());
        result = prime * result + getTemperature();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("temperature=").append(getTemperature())
                .append(", light=").append(getLight())
                .append("}")
                .toString();
    }
}