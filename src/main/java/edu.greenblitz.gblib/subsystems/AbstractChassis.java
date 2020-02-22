package edu.greenblitz.gblib.subsystems;

import edu.greenblitz.gblib.encoder.IEncoder;
import edu.greenblitz.gblib.gears.Gear;
import edu.greenblitz.gblib.gyroscope.IGyroscope;

public abstract class AbstractChassis extends GBSubsystem {

    protected IEncoder leftEncoder, rightEncoder;
    protected IGyroscope gyroscope;

    public abstract void tankDrive(double leftPower, double rightPower);

    public void resetGyro(){
        gyroscope.reset();
    }

    public abstract double getWheelsDistance();

    public double getLeftVelocity(){
        return leftEncoder.getNormalizedVelocity();
    }

    public double getRightVelocity(){
        return rightEncoder.getNormalizedVelocity();
    }

    public double getAngleRads(){
        return gyroscope.getNormalizedYaw();
    }

    public double getLeftMeters(){
        return leftEncoder.getNormalizedTicks();
    }

    public double getRightMeters(){
        return rightEncoder.getNormalizedTicks();
    }

    public abstract void switchGear(Gear gear);

    public abstract void toBrake();

    public abstract void toCoast();

    public double getAngleDegs() {
        return Math.toDegrees(getAngleRads());
    }

    public void arcadeDrive(double forward, double turn) {
        tankDrive(forward - turn, forward + turn);
    }

    public double getLinearVelocity() {
        return 0.5 * (getRightVelocity() + getLeftVelocity());
    }

    public double getAngularVelocity() {
        return getWheelsDistance() * (getRightVelocity() - getLeftVelocity());
    }

    @Override
    public void periodic() {
        super.periodic();
        putNumber("Left Velocity", getLeftVelocity());
        putNumber("Right Velocity", getRightVelocity());
        putNumber("Angular Velocity", getAngularVelocity());
        putNumber("Linear Velocity", getLinearVelocity());
        putNumber("Angle (Degrees)", getAngleDegs());
    }
}
