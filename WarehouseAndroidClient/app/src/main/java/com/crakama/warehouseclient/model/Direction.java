package com.crakama.warehouseclient.model;

/**
 * Created by kate on 05/05/2018.
 * ##Art
 * stores all the data that will be show on expand view
 */
public class Direction {

    private String status;
    private String descriptions;

    public Direction(String status, String descriptions) {
        this.status = status;
        this.descriptions = descriptions;
    }

    public Direction() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
