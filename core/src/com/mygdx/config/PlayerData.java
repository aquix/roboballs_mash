package com.mygdx.config;

import com.mygdx.game_helpers.SaveManager;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerData implements Serializable {
    private static PlayerData instance;

    private PlayerData() {
        unlockedLevelsCount = 1;
        completedLevelsCount = 0;
        completedLevels = new ArrayList<CompletedLevel>();
    }

    public static PlayerData getInstance() {
        if (instance == null) {
            if (SaveManager.isSavedUserDataExists()) {
                instance = SaveManager.loadUserData();
            } else {
                instance = new PlayerData();
                SaveManager.saveUserData(instance);
            }
        }
        return instance;
    }

    private int unlockedLevelsCount;
    private int completedLevelsCount;
    private ArrayList<CompletedLevel> completedLevels;

    public int getUnlockedLevelsCount() {
        return unlockedLevelsCount;
    }

    public ArrayList<CompletedLevel> getCompletedLevels() {
        return completedLevels;
    }

    public int getCompletedLevelsCount() {
        return completedLevelsCount;
    }

    public void savePlayerData() {
        SaveManager.saveUserData(instance);
    }

    public void levelCompleted(int levelNumber, CompletedLevel stars) {
        if (levelNumber == unlockedLevelsCount) {
            if (unlockedLevelsCount < Configuration.totalGameLevels) {
                unlockedLevelsCount++;
            }
            completedLevelsCount++;
            completedLevels.add(stars);
        } else {
            if (completedLevels.get(levelNumber - 1).value < stars.value) {
                completedLevels.set(levelNumber - 1, stars);
            }
        }
    }
}
