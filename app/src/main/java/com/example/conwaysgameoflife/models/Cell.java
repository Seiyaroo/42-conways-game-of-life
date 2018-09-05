package com.example.conwaysgameoflife.models;

public class Cell {
    public int x;
    public int y;

    public boolean live;

    public Cell (int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.live = live;
    }

    public void die() {
        live = false;
    }

    public void reborn() {
        live = true;
    }

    public void inverted() {
        live = !live;
    }
}
