package edu.greenblitz.gblib.encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.greenblitz.gblib.gears.GearDependentValue;

public class SparkEncoder extends AbstractEncoder {
    private static final int SPARK_COUNT_RATIO = 42;
    private RelativeEncoder m_sparkEncoder;
    private int m_nullPosition;

    public SparkEncoder(GearDependentValue<Double> normalizeConst, CANSparkMax sparkEncoder) {
        super(normalizeConst);
        m_sparkEncoder = sparkEncoder.getEncoder();
        m_nullPosition = 0;
    }

    @Override
    public void reset() {
        m_nullPosition = get();
    }

    /**
     * OK here me out on this, the spark returns the ticks in multiples of 0.023809523809523808 (1/42),
     * which is obviously a double, and to match {@link IEncoder IEncoder's} documentation, we have to multiply :(
     * @return SparkMax encoder's raw tick count
     */
    private int get() {
        return (int) Math.round(m_sparkEncoder.getPosition() * SPARK_COUNT_RATIO);
    }

    @Override
    public double getRawTicks() {
        return get() - m_nullPosition;
    }

    @Override
    public double getTickRate() {
        return m_sparkEncoder.getVelocity() * SPARK_COUNT_RATIO / 60.0;
    }
}