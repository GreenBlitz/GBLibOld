package edu.greenblitz.gblib.encoder;

import edu.greenblitz.gblib.gears.GearDependentValue;
import edu.wpi.first.wpilibj.Encoder;

public class RoborioEncoder extends AbstractEncoder {
    private Encoder m_encoder;

    public RoborioEncoder(GearDependentValue<Double> normalizeConst, Encoder encoder) {
        super(normalizeConst);
        m_encoder = encoder;
    }

    public RoborioEncoder(GearDependentValue<Double> normalizeCost, int channelA, int channelB) {
        this(normalizeCost, new Encoder(channelA, channelB));
    }

    @Override
    public void reset() {
        m_encoder.reset();
    }

    @Override
    public int getRawTicks() {
        return m_encoder.getRaw();
    }

    @Override
    public double getTickRate() {
        return m_encoder.getRate();
    }
}
