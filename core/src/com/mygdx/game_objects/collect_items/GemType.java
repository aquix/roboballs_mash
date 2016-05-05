package com.mygdx.game_objects.collect_items;

public enum GemType {
    LITTLE(5), BIG(10);

    private final int type;
    GemType(int type) { this.type = type; }
    public int getValue() { return type; }
}
