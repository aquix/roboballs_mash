package com.mygdx.game_objects.robots;

import com.mygdx.game_objects.robots.Robot;

import java.lang.reflect.Constructor;

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
