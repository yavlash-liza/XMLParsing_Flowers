package com.company.yavlash.entity;

import com.company.yavlash.entity.enums.Multiplying;
import com.company.yavlash.entity.enums.SoilType;
import com.company.yavlash.entity.enums.WaterType;

import java.time.LocalDate;

public class AquaticFlower extends Flower{
    private WaterType waterType;

    public AquaticFlower() {
    }

    public AquaticFlower(String name, SoilType soilType, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, LocalDate plantingDate, String id, WaterType waterType) {
        super(name, soilType, origin, visualParameters, growingTips, multiplying, plantingDate, id);
        this.waterType = waterType;
    }

    public WaterType getWaterType() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        AquaticFlower aThat = (AquaticFlower) object;
        if(!super.equals(object)){return false;}
        if(getWaterType() == null) {
            return aThat.getWaterType() == null;
        } else return getWaterType().equals(aThat.getWaterType());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getWaterType() == null ? 0 : getWaterType().hashCode());;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("name=").append(getName())
                .append(", soilType=").append(getSoilType())
                .append(", origin=").append(getOrigin())
                .append(", visualParameters=").append(getVisualParameters())
                .append(", growingTips=").append(getGrowingTips())
                .append(", multiplying=").append(getMultiplying())
                .append(", plantingDate=").append(getPlantingDate())
                .append(", id=").append(getId())
                .append(", waterType=").append(getWaterType())
                .append("}")
                .toString();
    }
}