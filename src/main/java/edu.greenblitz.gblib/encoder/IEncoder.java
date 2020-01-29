package edu.greenblitz.gblib.encoder;

public interface IEncoder {

    /**
     * Resets the encoder. if the motor hasn't moved after this was called, getRawTicks is expected to return 0.
     * Calling this while the motor is moving might be problematic.
     */
    void reset();

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

    double getNormalizeConst();

    /**
     *
     * @param value the value to normalize by
     */
    void setNormalizeConst(double value);

    /**
     *
     * @return raw ticks divided by normalize const and inverted as needed.
     */
    default double getNormalizedTicks() {
        return getRawTicks() * invert() / getNormalizeConst();
    }

    /**
     *
     * @return velocity after conversion in m/s
     */
    default double getNormalizedVelocity() {
        return getTickRate() * invert() / getNormalizeConst();
    }

    void invert(boolean inverted);

    boolean isInverted();

    default int invert() {
        return isInverted() ? -1 : 1;
    }
}