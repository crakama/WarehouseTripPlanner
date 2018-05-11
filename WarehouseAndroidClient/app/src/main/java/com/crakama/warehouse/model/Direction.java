package com.crakama.warehouse.model;

/**
 * Created by kate on 05/05/2018.
 * ##Art
 * stores all the data that will be show on expand view
 */
public class Direction {
    private String row;
    private String column;
    private String height;

    public Direction(String row, String column, String height) {
        this.row =row;
        this.column = column;
        this.height = height;
    }

    public String getRow() {
        return row;
    }
    public String getColumn() {
        return column;
    }
    public String getHeight() {
        return height;
    }
    public void setRow(String row) {
        this.row = row;
    }
    public void setColumn(String col) {
        this.column = col;
    }
    public void setHeight(String height) {
        this.height = height;
    }
}
