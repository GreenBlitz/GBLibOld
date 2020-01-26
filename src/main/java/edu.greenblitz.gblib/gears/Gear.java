package edu.greenblitz.gblib.gears;

/**
 * This is an enum that works based on the state of piston.
 * POWER - Piston is in a forward state.
 * SPEED - Piston is in a reverse state.
 */
public enum Gear {
    POWER,
    SPEED;

    public boolean isSpeed() {
        return this == SPEED;
    }
    public boolean isPower() {
        return !isSpeed();
    }
}
