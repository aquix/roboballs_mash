package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygdx.config.PlayerData;
import com.mygdx.gameworld.GameWorld;

import java.io.*;

public class SaveManager {
    public static boolean saveLevel(GameWorldInfo worldInfo) {
        try {
            File saveFile = new File(String.valueOf(Gdx.files.internal("saves/saveLevel.rbs")));

            if(!saveFile.exists()) {
                boolean isFileCreated = saveFile.createNewFile();
                if (!isFileCreated) {
                    return false;
                }
            }

            ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream(saveFile,
                    false));
            saveStream.writeObject(worldInfo);
            saveStream.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static GameWorldInfo loadLevel() {
        try {
            File saveFile = new File(String.valueOf(Gdx.files.internal
                    ("saves/saveLevel.rbs")));

            ObjectInputStream saveStream = new ObjectInputStream(new
                    FileInputStream(saveFile));

            GameWorldInfo info = (GameWorldInfo) saveStream.readObject();
            saveStream.close();
            saveFile.delete();
            return info;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean isSavedLevelExists() {
        File saveFile = new File(String.valueOf(Gdx.files.internal
                ("saves/saveLevel.rbs")));
        return saveFile.exists();
    }

    public static boolean saveUserData(PlayerData playerData) {
        try {
            File saveFile = new File(String.valueOf(Gdx.files.internal
                    ("saves/userdata.rbs")));

            if(!saveFile.exists()) {
                boolean isFileCreated = saveFile.createNewFile();
                if (!isFileCreated) {
                    return false;
                }
            }

            ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream(saveFile,
                    false));
            saveStream.writeObject(playerData);
            saveStream.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static PlayerData loadUserData() {
        try {
            File saveFile = new File(String.valueOf(Gdx.files.internal
                    ("saves/userdata.rbs")));

            ObjectInputStream saveStream = new ObjectInputStream(new
                    FileInputStream(saveFile));

            PlayerData playerData = (PlayerData) saveStream.readObject();
            saveStream.close();
            return playerData;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean isSavedUserDataExists() {
        File saveFile = new File(String.valueOf(Gdx.files.internal
                ("saves/userdata.rbs")));
        return saveFile.exists();
    }
}
