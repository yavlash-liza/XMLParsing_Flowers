package com.company.yavlash.entity;

import com.company.yavlash.entity.enums.LeavesForm;
import com.company.yavlash.entity.enums.Root;

public class VisualParameters {
    private LeavesForm leavesForm;
    private Root root;
    private int averageHeight;

    public VisualParameters() {
    }

    public VisualParameters(LeavesForm leavesForm, Root root, int averageHeight) {
        this.leavesForm = leavesForm;
        this.root = root;
        this.averageHeight = averageHeight;
    }

    public LeavesForm getLeavesForm() {
        return leavesForm;
    }

    public void setLeavesForm(LeavesForm leavesForm) {
        this.leavesForm = leavesForm;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public int getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(int averageHeight) {
        this.averageHeight = averageHeight;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        VisualParameters aThat = (VisualParameters) object;

        if(getLeavesForm() == null) {
            if(aThat.getLeavesForm() != null){return false;}
        } else if(!getLeavesForm().equals(aThat.getLeavesForm())){return false;}

        if(getRoot() == null) {
            if(aThat.getRoot() != null){return false;}
        } else if(!getRoot().equals(aThat.getRoot())){return false;}
        return getAverageHeight() == aThat.getAverageHeight();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getLeavesForm() == null ? 0 : getLeavesForm().hashCode());
        result = prime * result + (getRoot() == null ? 0 : getRoot().hashCode());
        result = prime * result + getAverageHeight();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("leavesForm=").append(getLeavesForm())
                .append(", root=").append(getRoot())
                .append(", averageHeight=").append(getAverageHeight())
                .append("}")
                .toString();
    }
}