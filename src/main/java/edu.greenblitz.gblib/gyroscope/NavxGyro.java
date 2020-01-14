package edu.greenblitz.gblib.gyroscope;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class NavxGyro implements IGyroscope {

    private AHRS navx;
    private int inversed = 1;

    public NavxGyro(AHRS navx){
        this.navx = navx;
    }

    @Override
    public double getRawYaw() {
        return inversed * Math.toRadians(navx.getAngle());
    }

    @Override
    public void inverse() {
        inversed *= -1;
    }

    @Override
    public double getYawRate() {
        return Math.toRadians(navx.getRate());
    }

    @Override
    public void reset() {
        navx.reset();
    }

    public AHRS getNavx(){
        return navx;
    }
}
