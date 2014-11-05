package org.exercise.coffee.model;

public enum Priority {

    NORMAL(1),
    HIGH(0);

    private final int value;

    private Priority (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
