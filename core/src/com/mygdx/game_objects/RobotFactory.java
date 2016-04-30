package com.mygdx.game_objects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RobotFactory {
    public static Robot createRobot(Class type, float x, float y) {
        try {
            Constructor<?> ctor = type.getConstructor(float.class, float.class);
            return (Robot) ctor.newInstance(x, y);
        } catch (Exception e) {
            return null;
        }
    }
}
