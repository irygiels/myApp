package irygiels.myapplication;

import android.util.Log;

/**
 * Created by irygiels on 05.10.15.
 */
public class NmeaParser {

    public double longitude;
    public double latitude;

    NmeaParser(){
    }

    NmeaParser(String modifiedSentence) {

        String[] strValues = modifiedSentence.split(",");
        if (modifiedSentence.startsWith("$GPRMC")) {
            Log.i("wartosc", String.valueOf(strValues[3]));

                    latitude = Double.parseDouble(strValues[3]) * .01;
            if (strValues[4].charAt(0) == 'S') {
                latitude = -latitude;
            }
            longitude = Double.parseDouble(strValues[5])*.01;
            if (strValues[6].charAt(0) == 'W') {
                longitude = -longitude;
            }
         /*   double course = Double.parseDouble(strValues[8]);
            System.out.println("latitude="+latitude+" ; longitude="+longitude+" ; course = "+course);*/
        }else if (modifiedSentence.startsWith("$GPGGA")) {
            latitude = Double.parseDouble(strValues[2]) * .01;
            if (strValues[3].charAt(0) == 'S') {
                latitude = -latitude;
            }
            longitude = Double.parseDouble(strValues[4]) * .01;
            if (strValues[5].charAt(0) == 'W') {
                longitude = -longitude;
            }
        }else if (modifiedSentence.startsWith("$GPGLL")) {
            latitude = Double.parseDouble(strValues[1]) * .01;
            if (strValues[2].charAt(0) == 'S') {
                latitude = -latitude;
            }
            longitude = Double.parseDouble(strValues[3]) * .01;
            if (strValues[4].charAt(0) == 'W') {
                longitude = -longitude;
            }
        }else if (modifiedSentence.startsWith("$GPWPL")) {
            latitude = Double.parseDouble(strValues[1]);
            if (strValues[2].charAt(0) == 'S') {
                latitude = -latitude;
            }
            longitude = Double.parseDouble(strValues[3]);
            if (strValues[4].charAt(0) == 'W') {
                longitude = -longitude;
            }
        }else if (modifiedSentence.startsWith("$GPBWC")) {
            latitude = Double.parseDouble(strValues[2]);
            if (strValues[3].charAt(0) == 'S') {
                latitude = -latitude;
            }
            longitude = Double.parseDouble(strValues[4]);
            if (strValues[5].charAt(0) == 'W') {
                longitude = -longitude;
            }
        }else if (modifiedSentence.startsWith("$GPRMB")) {
            latitude = Double.parseDouble(strValues[6]) * .01;
            if (strValues[7].charAt(0) == 'S') {
                latitude = -latitude;
            }
            longitude = Double.parseDouble(strValues[8]) * .01;
            if (strValues[9].charAt(0) == 'W') {
                longitude = -longitude;
            }
        }

        }





}


