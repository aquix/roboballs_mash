package com.mygdx.lang_helpers;

import com.mygdx.game_objects.IAliveable;

import java.util.ArrayList;

public class DeadObjectsRemover {
    public static ArrayList<IAliveable> tempList;

    static {
        tempList = new ArrayList<IAliveable>();
    }

    public static void removeDead(ArrayList<? extends IAliveable>
            arrayList) {
        for (IAliveable elem : arrayList) {
            if (!elem.isAlive()) {
                tempList.add(elem);
            }
        }

        for (IAliveable elem : tempList) {
            arrayList.remove(elem);
        }

        tempList.clear();
    }
}
