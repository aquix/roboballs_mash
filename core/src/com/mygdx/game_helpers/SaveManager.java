package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mygdx.gameworld.GameWorld;

import java.io.*;

public class SaveManager {
    public static boolean save(GameWorldInfo worldInfo) {
        try {
            File saveFile = new File(String.valueOf(Gdx.files.internal("saves/save.rbs")));

            if(!saveFile.exists()) {
                boolean isFileCreated = saveFile.createNewFile();
                if (!isFileCreated) {
                    return false;
                }
            }

            ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream(saveFile,
                    false));
            saveStream.writeObject(worldInfo);
            saveStream.flush();
            saveStream.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static GameWorldInfo load() {
        try {
            ObjectInputStream saveStream = new ObjectInputStream(new
                    FileInputStream(String.valueOf(Gdx.files.internal("saves/save" +
                    ".rbs"))));
            return (GameWorldInfo) saveStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
