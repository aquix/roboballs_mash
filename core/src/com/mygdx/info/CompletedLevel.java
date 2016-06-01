package com.mygdx.info;

public enum CompletedLevel {
    ONE_STAR(1), TWO_STARS(2), THREE_STARS(3);

    public final int value;
    CompletedLevel(int stars) { this.value = stars; }
}
