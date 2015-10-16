package irygiels.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * Created by irygiels on 30.09.15.
 */
public class Sensor extends AppCompatActivity {


    public Sensor(){}

    String modifiedSentence="";
    //to sa wartosci, jakie moge odczytac z sensora
    private String accel_x;
    private String accel_y;
    private String accel_z;
    private String mac_id;
    private int id;

    //tagi, po ktorych moge zebrac poszczegolne wartosci
    private static final String tag_accel_x = "accel_x";
    private static final String tag_accel_y = "accel_y";
    private static final String tag_accel_z = "accel_z";
    private static final String tag_mac_id = "mac_id";
    private static final String tag_id = "id";

    public String getAccel_x(){
        return accel_x;
    }
    public String getAccel_y(){
        return accel_y;
    }
    public String getAccel_z(){
        return accel_z;
    }
    public String getMac_id(){
        return mac_id;
    }
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAccel_x(String accel_x){
        this.accel_x=accel_x;
    }
    public void setAccel_y(String accel_y){
        this.accel_y=accel_y;
    }
    public void setAccel_z(String accel_z){
        this.accel_z=accel_z;
    }
    public void setMac_id(String mac_id){
        this.mac_id=mac_id;
    }


    //metody nizej pozwalaja na "wyciagniecie" wartosci z sensora

    public void setTxtaccel_x() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            accel_x = jsonObj.getString(tag_accel_x);
            //przerzucanie do bazki
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtaccel_y() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            accel_y = jsonObj.getString(tag_accel_y);
            //przerzucanie do bazki
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtaccel_z() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            accel_z = jsonObj.getString(tag_accel_z);
            //przerzucanie do bazki
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtmac_id() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            mac_id = jsonObj.getString(tag_mac_id);
            //przerzucanie do bazki
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtid() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            id = Integer.parseInt(jsonObj.getString(tag_id));
            //przerzucanie do bazki
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Sensor [accel_x=" + accel_x + ", accel_y=" + accel_y + ", accel_z=" + accel_z + ", mac_id"
                + "]";
    }


}
