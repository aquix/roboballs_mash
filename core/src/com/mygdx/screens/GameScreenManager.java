package com.mygdx.screens;

import java.util.Stack;

/**
 * Created by Vlad on 4/15/2016.
 */
public class GameScreenManager {
    private Stack<GameScreen> screens;

    public GameScreenManager() {
        screens = new Stack<GameScreen>();
    }

    public void push(GameScreen screen) {
        screens.push(screen);
    }

    public void pop() {
        screens.pop();
    }

    public void set(GameScreen screen) {
        screens.pop();
        screens.push(screen);
    }

    public void update(float delta) {
        screens.peek().render(delta);
    }
}
