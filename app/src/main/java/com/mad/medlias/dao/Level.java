package com.mad.medlias.dao;

/**
 * Created by a2881dante on 04.08.2017.
 */

public class Level {

    private int id;
    private String name;

    public Level() {}

    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
