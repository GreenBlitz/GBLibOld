package edu.greenblitz.gblib.i2c;

import edu.wpi.first.wpilibj.I2C;

import java.util.Arrays;
public class GBi2c {
    static I2C Rpi = new I2C(I2C.Port.kOnboard, 1);

    public static byte[] getLength(byte[] data){
        int ln = data.length;
        byte[] arrLength = new byte[]{0, 0, 0, 0};
        for(int i=0;i<4; i++){
            arrLength[3-i] = (byte)(ln & 0xFF);
            ln >>= 8;
        }
        return arrLength;

    }
    public static int readLength(byte[] data){
        int result = 0;
        for(int i = 0; i<4; i++){
            result <<= 8;
            result |= data[i];
        }
        return result;

    }
    public static boolean send(byte[] data) {
        return !Rpi.writeBulk(data);
    }
    public static byte[] receive(int address){
        byte[] length = new byte[4];
        Rpi.read(address, 4, length);
        byte[] data = new byte[GBi2c.readLength(length)];
        Rpi.read(address, GBi2c.readLength(length), data);
        if(Arrays.equals(length, Arrays.copyOfRange(data, 0, 4))){
            System.out.println("please take add 4 to length and remove first four bytes in data");
        }
        return data;
    }

    /*public static Tuple<Double, Double> targetTransaction() {
        byte[] reciveBuffer = new byte[16];
        byte[] targetpls = new byte[]{1};
        boolean check = Rpi.transaction(targetpls, 1, reciveBuffer, 16);
        if (check) {
            return null;
        }
        double dist = ByteBuffer.wrap(reciveBuffer, 0, 8).getDouble();
        double angle = ByteBuffer.wrap(reciveBuffer, 8, 8).getDouble();
        Tuple<Double, Double> output = new Tuple<>(dist, angle);
        return (output);
    }*/

}