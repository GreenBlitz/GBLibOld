package edu.greenblitz.gblib.RS232;

import java.util.Arrays;
public class RS232 {

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

}
//https://github.com/WilliamHuang-cn/2017FRCVisionTrial/wiki/Using-a-coprocessor-with-RoboRIO