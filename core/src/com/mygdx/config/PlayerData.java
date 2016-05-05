package com.mygdx.config;

public class PlayerData {
    private static PlayerData instance;

    private PlayerData() {
        availableLevels = 2;
    }

    public static PlayerData getInstance() {
        if (instance == null) {
            instance = new PlayerData();
        }
        return instance;
    }

    private int availableLevels;

    public int getAvailableLevels() {
        return availableLevels;
    }
}
