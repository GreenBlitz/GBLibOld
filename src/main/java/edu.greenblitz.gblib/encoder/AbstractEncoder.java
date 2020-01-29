package edu.greenblitz.gblib.encoder;

import edu.greenblitz.gblib.gears.Gear;
import edu.greenblitz.gblib.gears.GearDependentValue;

public abstract class AbstractEncoder implements IEncoder {

    private GearDependentValue<Double> m_normalizeConst;
    private boolean m_inverted;
    private int accumulatedTicks;
    private double accumulatedDistance;

    /**
     * This constructor receives the normalize constant of the motor controller.
     * It also checks to see if the constant valid as well.
     *
     * @param normalizeConst A double of the ticks per meter of movement.
     */
    public AbstractEncoder(GearDependentValue<Double> normalizeConst) {
        if (normalizeConst.getValue(Gear.POWER) == +0.0 || !Double.isFinite(normalizeConst.getValue(Gear.POWER)))
            throw new IllegalArgumentException("invalid ticks per meter value '" + normalizeConst + "'");
        if (normalizeConst.getValue(Gear.SPEED) == +0.0 || !Double.isFinite(normalizeConst.getValue(Gear.SPEED)))
            throw new IllegalArgumentException("invalid ticks per meter value '" + normalizeConst + "'");

        accumulatedTicks = 0;
        accumulatedDistance = 0;
        m_normalizeConst = normalizeConst;
    }

    @Override
    public void switchGear(){
        accumulatedDistance = getNormalizedTicks();
        accumulatedTicks = getRawTicks();
    }

    @Override
    public GearDependentValue<Double> getNormalizeConst() {
        return m_normalizeConst;
    }

    @Override
    public void setNormalizeConst(GearDependentValue<Double> value) {
        m_normalizeConst = value;
    }

    @Override
    public double getNormalizedTicks() {
        return ((getRawTicks() - accumulatedTicks) * invert() / getNormalizeConst().getValue()) + accumulatedDistance;
    }

    @Override
    public void invert(boolean inverted) {
        m_inverted = inverted;
    }

    @Override
    public boolean isInverted() {
        return m_inverted;
    }
}
