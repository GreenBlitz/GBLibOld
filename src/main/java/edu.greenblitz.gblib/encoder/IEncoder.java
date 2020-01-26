package edu.greenblitz.gblib.encoder;

import edu.greenblitz.gblib.gears.GearDependentValue;

public interface IEncoder {

    /**
     * Resets the encoder. if the motor hasn't moved after this was called, getRawTicks is expected to return 0.
     * Calling this while the motor is moving might be problematic.
     */
    void reset();

    /**
     * Call this to do any needed commands when switching gears.
     */
    void switchGear();

    /**
     *
     * @return raw amount of ticks so far
     */
    int getRawTicks();

    /**
     *
     * @return ticks per second
     */
    double getTickRate();

    GearDependentValue<Double> getNormalizeConst();

    void setNormalizeConst(GearDependentValue<Double> value);

    default double getNormalizedTicks() {
        return getRawTicks() * invert() / getNormalizeConst().getValue();
    }

    /**
     *
     * @return velocity after conversion in m/s
     */
    default double getNormalizedVelocity() {
        return getTickRate() * invert() / getNormalizeConst().getValue();
    }

    void invert(boolean inverted);

    boolean isInverted();

    default int invert() {
        return isInverted() ? -1 : 1;
    }
}