package com.company.yavlash.entity;

import com.company.yavlash.entity.enums.FlowerAge;
import com.company.yavlash.entity.enums.Multiplying;
import com.company.yavlash.entity.enums.OriginType;
import com.company.yavlash.entity.enums.SoilType;

import java.time.LocalDate;

public class GardenFlower extends Flower {
    private FlowerAge flowerAge;

    public GardenFlower() {
    }

    public GardenFlower(String flowerName, SoilType soilType, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, LocalDate plantingDate, String flowerId, String flowerInfo, OriginType originType) {
        super(flowerName, soilType, origin, visualParameters, growingTips, multiplying, plantingDate, flowerId, flowerInfo);
        this.flowerAge = flowerAge;
    }

    public FlowerAge getFlowerAge() {
        return flowerAge;
    }

    public void setFlowerAge(FlowerAge flowerAge) {
        this.flowerAge = flowerAge;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        GardenFlower aThat = (GardenFlower) object;
        if(!super.equals(object)){return false;}
        if(getFlowerAge() == null) {
            return aThat.getFlowerAge() == null;
        } else return getFlowerAge().equals(aThat.getFlowerAge());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getFlowerAge() == null ? 0 : getFlowerAge().hashCode());;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("flowerName=").append(getFlowerName())
                .append(", soilType=").append(getSoilType())
                .append(", origin=").append(getOrigin())
                .append(", visualParameters=").append(getVisualParameters())
                .append(", growingTips=").append(getGrowingTips())
                .append(", multiplying=").append(getMultiplying())
                .append(", plantingDate=").append(getPlantingDate())
                .append(", flowerId=").append(getFlowerId())
                .append(", flowerInfo=").append(getFlowerInfo())
                .append(", flowerAge=").append(getFlowerAge())
                .append("}")
                .toString();
    }
}