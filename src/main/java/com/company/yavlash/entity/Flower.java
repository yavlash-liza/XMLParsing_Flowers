package com.company.yavlash.entity;

import com.company.yavlash.entity.enums.Multiplying;
import com.company.yavlash.entity.enums.SoilType;

import java.time.LocalDate;

public abstract class Flower {
    private String flowerName;
    private SoilType soilType;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Multiplying multiplying;
    private LocalDate plantingDate;
    private String flowerId;
    private String flowerInfo;

    public Flower() {
        visualParameters = new VisualParameters();
        growingTips = new GrowingTips();
    }

    public Flower(String flowerName, SoilType soilType, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, LocalDate plantingDate, String flowerId, String flowerInfo) {
        this.flowerName = flowerName;
        this.soilType = soilType;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
        this.plantingDate = plantingDate;
        this.flowerId = flowerId;
        this.flowerInfo = flowerInfo;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerInfo() {
        return flowerInfo;
    }

    public void setFlowerInfo(String flowerId) {
        this.flowerInfo = flowerInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        Flower aThat = (Flower) object;

        if(getFlowerName() == null) {
            if(aThat.getFlowerName() != null){return false;}
        } else if(!getFlowerName().equals(aThat.getFlowerName())){return false;}

        if(getSoilType() == null) {
            if(aThat.getSoilType() != null){return false;}
        } else if(!getSoilType().equals(aThat.getSoilType())){return false;}

        if(getOrigin() == null) {
            if(aThat.getOrigin() != null){return false;}
        } else if(!getOrigin().equals(aThat.getOrigin())){return false;}

        if(getVisualParameters() == null) {
            if(aThat.getVisualParameters() != null){return false;}
        } else if(!getVisualParameters().equals(aThat.getVisualParameters())){return false;}

        if(getGrowingTips() == null) {
            if(aThat.getGrowingTips() != null){return false;}
        } else if(!getGrowingTips().equals(aThat.getGrowingTips())){return false;}

        if(getMultiplying() == null) {
            if(aThat.getMultiplying() != null){return false;}
        } else if(!getMultiplying().equals(aThat.getMultiplying())){return false;}

        if(getPlantingDate() == null) {
            if(aThat.getPlantingDate() != null){return false;}
        } else if(!getPlantingDate().equals(aThat.getPlantingDate())){return false;}

        if(getFlowerInfo() == null) {
            if(aThat.getFlowerInfo() != null){return false;}
        } else if(!getFlowerInfo().equals(aThat.getFlowerInfo())){return false;}

        if(getFlowerId() == null) {
            return aThat.getFlowerId() == null;
        } else return getFlowerId().equals(aThat.getFlowerId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getFlowerName() == null ? 0 : getFlowerName().hashCode());
        result = prime * result + (getSoilType() == null ? 0 : getSoilType().hashCode());
        result = prime * result + (getOrigin() == null ? 0 : getOrigin().hashCode());
        result = prime * result + (getVisualParameters() == null ? 0 : getVisualParameters().hashCode());
        result = prime * result + (getGrowingTips() == null ? 0 : getGrowingTips().hashCode());
        result = prime * result + (getMultiplying() == null ? 0 : getMultiplying().hashCode());
        result = prime * result + (getPlantingDate() == null ? 0 : getPlantingDate().hashCode());
        result = prime * result + (getFlowerId() == null ? 0 : getFlowerId().hashCode());
        result = prime * result + (getFlowerInfo() == null ? 0 : getFlowerInfo().hashCode());
        return result;
    }
}