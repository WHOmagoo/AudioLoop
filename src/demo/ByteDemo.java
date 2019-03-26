package demo;

import java.math.BigInteger;

public class ByteDemo {
    public static void main(String[] args) {
        for(int i = 0; i < 255; i++){
//            for(int j = 0; j < 255; j++) {


                char ic = (char) i;
//                char jc = (char) j;
                int curInt = ic << 24;
                curInt >>= 24;
                byte newB = (byte) curInt;

                if (newB != (byte) i) {
                    System.out.println("Error");
//                }
            }
        }

//        for(int i = 0; i < 255; i++){
//            char b = (char) i;
//            int curInt = b;
//            if(i != curInt){
//                System.out.println("Error on " + i);
//            }
//        }
    }
}
