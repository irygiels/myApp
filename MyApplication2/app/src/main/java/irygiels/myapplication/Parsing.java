/*package irygiels.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
//import android.content.DialogInterface.OnClickListener;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by irygiels on 24.09.15.

public class Parsing extends AppCompatActivity implements OnClickListener {
    private Button bt1;
    private Button bt2;
    private Button bt5;
    private Button bt6;
    private static final int PORT = 6535;
    Asynct async;
    TextView txt1;
    TextView txtaccel_x;
    TextView txtaccel_y;
    TextView txtaccel_z;
    TextView txtmac_id;
    TextView txtid;
    //  TextView txt5;
    String accel_x;
    String accel_y;
    String accel_z;
    String mac_id;
    String id;
    double latitude;
    double longitude;
    private static final String tag_accel_x = "accel_x";
    private static final String tag_accel_y = "accel_y";
    private static final String tag_accel_z = "accel_z";
    private static final String tag_mac_id = "mac_id";
    private static final String tag_id = "id";
    String modifiedSentence;
    GoogleMap map;

    public class Parsowanie extends AppCompatActivity
            implements OnClickListener {
        private Button bt1;
        private Button bt2;
        private Button bt5;
        private Button bt6;
        private static final int PORT = 6535;
        Asynct async;
        TextView txt1;
        TextView txtaccel_x;
        TextView txtaccel_y;
        TextView txtaccel_z;
        TextView txtmac_id;
        TextView txtid;
        //  TextView txt5;
        String accel_x;
        String accel_y;
        String accel_z;
        String mac_id;
        String id;
        double latitude;
        double longitude;
        private static final String tag_accel_x = "accel_x";
        private static final String tag_accel_y = "accel_y";
        private static final String tag_accel_z = "accel_z";
        private static final String tag_mac_id = "mac_id";
        private static final String tag_id = "id";
        String modifiedSentence;
        GoogleMap map;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }
    txt1 = (TextView) findViewById(R.id.textView1);
    //txt5 = (TextView) findViewById(R.id.textView5);
    txtaccel_x = (TextView) findViewById(R.id.textView12);
    txtaccel_y = (TextView) findViewById(R.id.textView13);
    txtaccel_z = (TextView) findViewById(R.id.textView14);
    txtmac_id = (TextView) findViewById(R.id.textView15);
    txtid = (TextView) findViewById(R.id.textView11);
    bt1 = (Button) findViewById(R.id.button1);
    bt1.setOnClickListener(this);
    bt2 = (Button) findViewById(R.id.button2);
    bt2.setOnClickListener(this);
    bt5 = (Button) findViewById(R.id.button5);
    bt5.setOnClickListener(this);
    bt6 = (Button) findViewById(R.id.button6);
    bt6.setOnClickListener(this);
    async = new Asynct();

    //textIn.setText("oncreate");
}

   /* private void clear(View view) {
        txt1.setText("null");
    }

    private void tryme(View view) {
        asynctry = new Asynctry();
        asynctry.execute();
    }

    private void control(View view) {
        async=new Async();
        async.execute();

    }


    public void onClick(View v) { // Parameter v stands for the view that was clicked.

        // getId() returns this view's identifier.
        if (v.getId() == R.id.button1) {
            // setText() sets the string value of the TextView
            async = new Asynct();
            async.execute();
        } else if (v.getId() == R.id.button2) {
            txt1.setText("null");
        } else if (v.getId() == R.id.button5) {
            txt1.setText("OK");
        } else if (v.getId() == R.id.button6) {
            txt1.setText("lol");


        }
    }

public class Asynct extends AsyncTask<Void, byte[], Boolean> {

    public Asynct() {

    }

    // @Override
    // protected void onPreExecute() {
    //     Log.i("AsyncTask", "onPreExecute");
    // }
    @Override
    protected Boolean doInBackground(Void... params) {
        Log.i("AsyncTask", "doInBackground: Creating socket");

        try {
            DatagramSocket client_socket = new DatagramSocket(null);
            client_socket.setReuseAddress(true);
            client_socket.setBroadcast(true);
            client_socket.bind(new InetSocketAddress(PORT));
            byte[] receiveData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
            client_socket.receive(packet);
            modifiedSentence = new String(packet.getData());
            if (modifiedSentence.startsWith("$")) {
                ogarnijParsowanie();
                setUpMap(map);
            } else {
                wypluj();
                setTxtaccel_x();
                setTxtaccel_y();
                setTxtaccel_z();
                setTxtmac_id();
                setTxtid();
            }
            client_socket.close();
            while(modifiedSentence != null){
                //txt1.setText(modifiedSentence);
                JSONObject jsonObj = new JSONObject(modifiedSentence); // chce jsona ze stringa

                accel_x = jsonObj.getString(tag_accel_x);
                accel_y = jsonObj.getString(tag_accel_y);
                accel_z = jsonObj.getString(tag_accel_z);
                mac_id = jsonObj.getString(tag_mac_id);
                id = jsonObj.getString(tag_id);
                HashMap<String, String> element = new HashMap<String, String>();
                element.put(tag_accel_x, accel_x);
                element.put(tag_accel_y, accc);
                element.put(tag_accel_z, email);
                element.put(tag_mac_id, mobile);

                // adding contact to contact list
                contactList.add(contact);}
            wypluj();

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return true;
    }


}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void wypluj() {

        try {

            // txt1.setText(modifiedSentence);
            txt1.setText(modifiedSentence);
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

    public void setTxtaccel_x() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            accel_x = jsonObj.getString(tag_accel_x);
            txtaccel_x.setText(accel_x);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtaccel_y() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            accel_y = jsonObj.getString(tag_accel_y);
            txtaccel_y.setText(accel_y);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtaccel_z() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            accel_z = jsonObj.getString(tag_accel_z);
            txtaccel_z.setText(accel_z);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtmac_id() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            mac_id = jsonObj.getString(tag_mac_id);
            txtmac_id.setText(mac_id);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void setTxtid() {
        try {
            JSONObject jsonObj = new JSONObject(modifiedSentence);
            id = jsonObj.getString(tag_id);
            txtid.setText(id);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void ogarnijParsowanie() {

        if (modifiedSentence.startsWith("$GPRMC")) {
            String[] strValues = modifiedSentence.split(",");
            double latitude = Double.parseDouble(strValues[3]) * .01;
            if (strValues[4].charAt(0) == 'S') {
                latitude = -latitude;
            }
            double longitude = Double.parseDouble(strValues[5]) * .01;
            if (strValues[6].charAt(0) == 'W') {
                longitude = -longitude;
            }
            double course = Double.parseDouble(strValues[8]);
        }}

    //mamy latitude i longitude, cieszymy sie
    //   } else if (modifiedSentence.startsWith("$GPGSV")) {
//robcostamcostam
    //    } //jesli dostajemy w paczce wszystkie, to bez ifa i bierzemy po kole
    public void setUpMap(GoogleMap map) {
        LatLng current = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(current).title("current position"));
        map.moveCamera(CameraUpdateFactory.newLatLng(current));
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

    }

}



public class MapsActivity extends FragmentActivity {

    int i;
    android.widget.ListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] ludzie = new String[6];


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new android.widget.ListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        setUpMapIfNeeded();
    }

    /*
     * Preparing the list data

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //tutaj musi byc petla for

        for (i=0; i<ludzie.length; i++){
            // Adding child data
            listDataHeader.add("Czlowiek " + Integer.toString(i));
            // Adding child data
            List<String> czlowiek = new ArrayList<String>();
            czlowiek.add("parametr pierwszy " + "wartosc parametru");
            czlowiek.add("parametr drugi " + "wartosc parametru");
            czlowiek.add("parametr trzeci " + "wartosc parametru");
            czlowiek.add("parametr czwarty " + "wartosc parametru");
            czlowiek.add("parametr piąty " + "wartosc parametru");

            listDataChild.put(listDataHeader.get(i), czlowiek); // Header, Child data
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
*/