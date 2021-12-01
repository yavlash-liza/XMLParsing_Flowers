package com.company.yavlash.entity;

import com.company.yavlash.entity.enums.Multiplying;
import com.company.yavlash.entity.enums.OriginType;
import com.company.yavlash.entity.enums.SoilType;

import java.time.LocalDate;

public class WildFlower extends Flower{
    private OriginType originType;

    public WildFlower() {
    }

    public WildFlower(String name, SoilType soilType, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, LocalDate plantingDate, String id, OriginType originType) {
        super(name, soilType, origin, visualParameters, growingTips, multiplying, plantingDate, id);
        this.originType = originType;
    }

    public OriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OriginType originType) {
        this.originType = originType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        WildFlower aThat = (WildFlower) object;
        if(!super.equals(object)){return false;}
        if(getOriginType() == null) {
            return aThat.getOriginType() == null;
        } else return getOriginType().equals(aThat.getOriginType());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getOriginType() == null ? 0 : getOriginType().hashCode());;
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
                .append(", originType=").append(getOriginType())
                .append("}")
                .toString();
    }
}