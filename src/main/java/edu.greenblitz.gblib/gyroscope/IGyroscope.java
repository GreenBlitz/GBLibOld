package edu.greenblitz.gblib.gyroscope;

public interface IGyroscope {

    private static double normalize(double angle){
        angle %= (2 * Math.PI);
        if (angle > Math.PI)
            angle -= 2 * Math.PI;
        if (angle <= -Math.PI)
            angle += 2 * Math.PI;
        return angle;
    }

    /**
     *
     * @return total accumulated yaw in radians, left is positive
     */
    double getRawYaw();

    /**
     *
     * @return yaw normalized between -Pi and Pi in radians, left is positive
     */
    default double getNormalizedYaw(){
        return normalize(getRawYaw());
    }

    /**
     *
     * @return The change in yaw in radians per second
     */
    double getYawRate();


    void reset();

}
