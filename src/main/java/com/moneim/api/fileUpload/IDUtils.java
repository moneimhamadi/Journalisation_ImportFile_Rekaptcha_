package com.moneim.api.fileUpload;

import java.util.Random;

public class IDUtils {


    public static String genImageName() {
        //Take the long integer value of the current time, including milliseconds
        long millis = System.currentTimeMillis();
        //Add three random numbers
        Random random = new Random();
        int end3 = random.nextInt(999);
        //If there are less than three digits, fill 0 in front
        String str = millis + String.format("%03d", end3);
        return str;
    }

}

