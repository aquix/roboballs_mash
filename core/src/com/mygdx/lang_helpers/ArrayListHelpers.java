package com.mygdx.lang_helpers;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;
import java.util.ArrayList;

public class ArrayListHelpers<T> implements Serializable {
    private ArrayList<T> tempList;

    public ArrayListHelpers() {
        this.tempList = new ArrayList<T>();
    }

    public void removeIf(ArrayList<T> arrayList, Predicate<T> predicate) {
        for (T elem : arrayList) {
            if (predicate.test(elem)) {
                tempList.add(elem);
            }
        }

        for (T elem : tempList) {
            arrayList.remove(elem);
        }

        tempList.clear();
    }
}
