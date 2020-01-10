package edu.greenblitz.gblib.gyroscope;

import com.ctre.phoenix.sensors.PigeonIMU;

public class PigeonGyro implements IGyroscope {

    private PigeonIMU birb;

    public PigeonGyro(PigeonIMU bird){
        birb = bird;
    }

    @Override
    public double getRawYaw() {
        double[] vals = new double[3];
        birb.getYawPitchRoll(vals);
        return Math.toRadians(vals[0]);
    }

    @Override
    public double getYawRate() {
        double[] vals = new double[3];
        birb.getRawGyro(vals);
        return Math.toRadians(vals[0]);
    }

    @Override
    public void reset() {
        birb.setYaw(0);
    }

    public PigeonIMU getPigeon(){
        return birb;
    }
}
