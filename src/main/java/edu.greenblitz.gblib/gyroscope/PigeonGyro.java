package edu.greenblitz.gblib.gyroscope;

import com.ctre.phoenix.sensors.PigeonIMU;

public class PigeonGyro implements IGyroscope {

    private PigeonIMU birb;
    private int inversed = 1;

    public PigeonGyro(PigeonIMU bird){
        birb = bird;
    }

    @Override
    public double getRawYaw() {
        double[] vals = new double[3];
        birb.getYawPitchRoll(vals);
        return Math.toRadians(vals[0]) * inversed;
    }

    @Override
    public void inverse() {
        inversed *= -1;
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
