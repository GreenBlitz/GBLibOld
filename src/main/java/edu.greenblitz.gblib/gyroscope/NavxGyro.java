package edu.greenblitz.gblib.gyroscope;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class NavxGyro implements IGyroscope {

    private AHRS navx;

    public NavxGyro(){
        this(SPI.Port.kMXP);
    }

    public NavxGyro(SPI.Port p){
        navx = new AHRS(p);
    }

    @Override
    public double getRawYaw() {
        return Math.toRadians(navx.getAngle());
    }

    @Override
    public double getYawRate() {
        return Math.toRadians(navx.getRate());
    }

    @Override
    public void reset() {
        navx.reset();
    }
}
