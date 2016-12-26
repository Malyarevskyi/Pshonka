package com.goitrestaurant.model;

public enum DeskStatus {

    FREE("FREE"),
    ORDERED("ORDERED");

    private String status;

    DeskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
