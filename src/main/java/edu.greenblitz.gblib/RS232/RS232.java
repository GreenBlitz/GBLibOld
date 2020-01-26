package edu.greenblitz.gblib.RS232;
import  edu.wpi.first.wpilibj.SerialPort;
import java.util.Arrays;
public class RS232 {
    SerialPort rs232;
    public RS232(){
    rs232 = new SerialPort(9600, SerialPort.Port.kOnboard);
    }
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
    public void testsend(){
        while (true) {
            rs232.write(new byte[]{2}, 1);
        }
    }
    public void send(byte[] data){

        //rs232.write(getLength(data), getLength(data).length);

    }

}
//https://github.com/WilliamHuang-cn/2017FRCVisionTrial/wiki/Using-a-coprocessor-with-RoboRIO