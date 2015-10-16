package irygiels.myapplication;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import java.security.Key;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnClickListener, LocationListener {

    //dane wyciagane z sensorow
    private String accel_x;
    private String accel_y;
    private String accel_z;
    private String mac_id;
    private int id;
    int licz=0;
    Boolean setMap = false;

    Timestamp timestamp;
    //marker mapy
    Marker marker;

    //tagi, po ktorych moge zebrac poszczegolne wartosci
    private static final String tag_accel_x = "accel_x";
    private static final String tag_accel_y = "accel_y";
    private static final String tag_accel_z = "accel_z";
    private static final String tag_mac_id = "mac_id";
    private static final String tag_id = "id";

    //czy kliknieta zostala ktoras opcja
    boolean machinesClick = false;
    boolean workersClick = false;
    boolean engineClick = false;
    public Database baza;
    NmeaParser nmeaParser;
    int i = 0;
    int j = 0;
    Adapter listAdapter;
    ExpandableListView expListView;
   // List<String> listDataHeader;
   // HashMap<String, List<String>> listDataChild;

    //te stringi nizej nie beda potrzebne
    String[] ludzie = new String[1];
    String[] maszyny = new String[1];

    double lat;
    double lon;
    private final Double TIME_IN_OURS = 0.5;
    private final long HOUR  = 60*60*1000;
    private Button fullscreen;
    private Button machines;
    private Button workers;
    private Button engine;
    SupportMapFragment mMap;
    View view;
    Boolean expand=false;
    // Sensor sensor = new Sensor();
    //string do pobranych danych
    String modifiedSentence;
    private volatile boolean networkConnection;
    //definicja asynctaska (do otworzenia gniazda)
    Asynct async;

    private Map<String,Timestamp> currentSensors;

    //dla ludzi
    private HashMap<String, Timestamp> listDataHeader = new HashMap<String, Timestamp>(); //naglowek - dataList
    HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>(); //tresc
    List<String> dataList = new ArrayList<String>();

    List<String> keyList = new ArrayList<>(listDataHeader.keySet());
    List<Timestamp> valueList = new ArrayList<>(listDataHeader.values());


    //dla maszyn
    private HashMap<String, Timestamp>  listDataHeaderm = new HashMap<String, Timestamp>(); //naglowek - dataList
    HashMap<String, List<String>> listDataChildm = new HashMap<String, List<String>>();
    List<String> dataListm = new ArrayList<String>();


    protected LocationManager locationManager;
    //port do nasluchiwania
    private static final int PORT = 6535;

     //sprawdzenie czy jest baza danych
     private static boolean doesDatabaseExist(Context context, String db) {
     File file = context.getDatabasePath(db);
     return file.exists();
     }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkConnection=true;
        baza=new Database(this);


        doesDatabaseExist(this, "baza");

      /*  String[] chmod = { "su", "-c","chmod 777 "+ path };
        try {
            Runtime.getRuntime().exec(chmod);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        if(doesDatabaseExist(this, "baza")==true){
            String path = this.getDatabasePath("baza").toString();
            Log.i("baza", "istnieje");
            Log.i("sciezka", path) ;
        } else {
            String path = this.getDatabasePath("baza").toString();
            Log.i("tworzenie", "nowa baza danych");
            Log.i("sciezka", path) ;

        }

        setContentView(R.layout.activity_maps);

        mMap = (SupportMapFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.map));

        fullscreen = (Button) findViewById(R.id.button);
        fullscreen.setOnClickListener(this);

        workers = (Button) findViewById(R.id.workers);
        workers.setOnClickListener(this);

        machines = (Button) findViewById(R.id.machines);
        machines.setOnClickListener(this);

        engine = (Button) findViewById(R.id.engine);
        engine.setOnClickListener(this);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        getLocation();
        setUpMap();

        Date date= new Date();
        timestamp = new Timestamp(date.getTime());
        listDataHeader.put("siema", timestamp);
    }

    @Override
    protected void onStart(){
        super.onStart();
        async = new Asynct();
        async.execute();
        prepareListData();
        //cleanOldSensors(TIME_IN_OURS);
    }

    @Override
    protected void onStop(){
        super.onStop();
        networkConnection = false;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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

            while(networkConnection==true) {
                try {

                    DatagramSocket client_socket = new DatagramSocket(null);
                    client_socket.setReuseAddress(true);
                    client_socket.setBroadcast(true);
                    client_socket.bind(new InetSocketAddress(PORT));
                    byte[] receiveData = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                    client_socket.receive(packet);
                    modifiedSentence = new String(packet.getData(), 0, packet.getLength());
                    Log.i("dostaje dane postaci", modifiedSentence);
                    /*String pierwszy = Character.toString(modifiedSentence.charAt(0));
                    Log.i("pierwszy znak to", pierwszy);*/

                        t.start();

                        u.start();

                    client_socket.close();

                } catch (Throwable e) {
                    e.printStackTrace();
                }


                //setUpMap();

                /*if (modifiedSentence.startsWith("$")) {
                    Log.i("parsuje", "nmee");
                    nmeaParser=new NmeaParser(modifiedSentence);
                    lat=nmeaParser.latitude;
                    lon=nmeaParser.longitude;
                    setUpMap();
                } else {
                    sensor.setTxtaccel_x();
                    sensor.setTxtaccel_y();
                    sensor.setTxtaccel_z();
                    sensor.setTxtmac_id();
                    sensor.setTxtid();
                    Log.d("set text", "dziala");
        }*/
            }

            return true;
        }


    }


    Thread t = new Thread() {

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(modifiedSentence.startsWith("$GPRMC") || modifiedSentence.startsWith("$GPGGA") || modifiedSentence.startsWith("GPGLL")
                                    || modifiedSentence.startsWith("$GPWPL") || modifiedSentence.startsWith("$GPWBC") || modifiedSentence.startsWith("GPRMB")){
                                nmeaParser = new NmeaParser(modifiedSentence);
                                lat = nmeaParser.latitude;
                                lon = nmeaParser.longitude;
                                if(lat!=0){
                                setUpMap();}
                                String str_lat = String.valueOf(lat);
                                String str_lon = String.valueOf(lon);
                                Log.i("position", str_lat+" "+str_lon);
                            }
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };

    Thread u = new Thread() {

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //parsowanie danych z photona
                            if(modifiedSentence.startsWith("{")) {
                                licz++;
                                setTxtaccel_x();
                                setTxtaccel_y();
                                setTxtaccel_z();
                                setTxtmac_id();
                                setTxtid();
                                Log.i("accel_x to", getAccel_x());
                                Log.i("accel_y to", getAccel_y());
                                Log.i("accel_z to", getAccel_z());
                                Log.i("mac_id to", getMac_id());
                                prepareListData();
                               /* if(licz%10==0) {
                                    //listAdapter = new Adapter(getApplicationContext(), listDataHeader, listDataChild);
                                    prepareListData();
                                    listAdapter = new Adapter(getApplicationContext(), listDataHeader, listDataChild);

                                    expListView.setAdapter(listAdapter);

                                }*/

                                //baza.insertData(getAccel_x(), getAccel_y(), getAccel_z(), getMac_id());
                            }
                            if(lat!=0){
                            setUpMap();}
                            Log.d("set text", "dziala");
                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };

    /*
     * Preparing the list data
     */
    private void prepareListData() {
       // String siema = listDataHeader.
       // Log.i("data list zawiera", siema.toString());
        Date date= new Date();
        timestamp = new Timestamp(date.getTime());
        Log.i("czas obecnie", timestamp.toString());

        //tutaj musi byc petla for

        //for (i=0; i<ludzie.length; i++){

            // adding child data - nie ma takigo mac_id - dodaj
            if(dataList.contains(getMac_id())==false && getMac_id()!=null) {
                dataList.add(getMac_id());
                // Adding child data
                List<String> czlowiek = new ArrayList<String>();


                czlowiek.add("accel x " + getAccel_x());
                czlowiek.add("accel y " + getAccel_y());
                czlowiek.add("accel z " + getAccel_z());
                czlowiek.add("mac id " + getMac_id());
                czlowiek.add("id ");
                Log.i("dodano", "dane czlowieka");
                listDataChild.put(getMac_id(), czlowiek); // Header, Child data
                listDataHeader.put(getMac_id(), timestamp);
                //i++;
            }

            //update jesli istnieje
            if(dataList.contains(getMac_id())==true) {
                listDataChild.remove(getMac_id()); //usuwam stare dane

                // Adding child data
                List<String> czlowiek = new ArrayList<String>();

                czlowiek.add("accel x " + getAccel_x());
                czlowiek.add("accel y " + getAccel_y());
                czlowiek.add("accel z " + getAccel_z());
                czlowiek.add("mac id " + getMac_id());
                czlowiek.add("id ");

                listDataChild.put(getMac_id(), czlowiek); // Header, Child data
                listDataHeader.remove(getMac_id());
                listDataHeader.put(getMac_id(), timestamp);
           // i++;
        }


        // }
    }
    private void prepareListMachines() {

    //    listDataHeader = new ArrayList<String>();
    //    listDataChild = new HashMap<String, List<String>>();

        //tutaj musi byc petla for

      //  for (j=0; i<maszyny.length; j++){
            // Adding child data
           /* listDataHeaderm.add("Maszyna " + Integer.toString(j));
            // Adding child data
            List<String> maszyna = new ArrayList<String>();
            maszyna.add("parametr pierwszy " + "wartosc parametru");
            maszyna.add("parametr drugi " + "wartosc parametru");
            maszyna.add("parametr trzeci " + "wartosc parametru");
            maszyna.add("parametr czwarty " + "wartosc parametru");
            maszyna.add("parametr piąty " + "wartosc parametru");

            listDataChildm.put(listDataHeaderm.get(j), maszyna); // Header, Child data
            j++;*/
        //}
    }

  /*  private void Engine() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //tutaj musi byc petla for

        for (i=0; i<ludzie.length; i++){
            // Adding child data
            listDataHeader.put("Czlowiek " + Integer.toString(i));
            // Adding child data
            List<String> czlowiek = new ArrayList<String>();
            czlowiek.add("parametr pierwszy " + "wartosc parametru");
            czlowiek.add("parametr drugi " + "wartosc parametru");
            czlowiek.add("parametr trzeci " + "wartosc parametru");
            czlowiek.add("parametr czwarty " + "wartosc parametru");
            czlowiek.add("parametr piąty " + "wartosc parametru");

            listDataChild.put(listDataHeader.get(i), czlowiek); // Header, Child data
        }
    }*/
   /* @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }*/

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
     */
 /*   private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mMap = (SupportMapFragment) (getSupportFragmentManager()
                    .findFragmentById(R.id.map));
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }*/


     /* This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

        private void setUpMap() {
            if(setMap==true) {
                marker.remove();
            }
            LatLng current = new LatLng(lat, lon);
                GoogleMap map = mMap.getMap();
                marker = map.addMarker(new MarkerOptions().position(current).title("You are here!"));
                map.moveCamera(CameraUpdateFactory.newLatLng(current));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(current)      // Sets the center of the map to Mountain View
                        .zoom(18)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                setMap=true;

    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            if (expand == false) {
                view = mMap.getView();
                ResizeAnimation resizeAnimation = new ResizeAnimation(view, 1000);
                resizeAnimation.setDuration(600);
                view.startAnimation(resizeAnimation);
                expand = true;
            } else {
                ResizeAnimation resizeAnimation = new ResizeAnimation(view, 300);
                resizeAnimation.setDuration(600);
                view.startAnimation(resizeAnimation);
                expand = false;
            }
        }else if(v.getId()==R.id.machines) {
            workers.setText("Workers");
            workersClick = false;
            prepareListMachines();
            listAdapter = new Adapter(this, dataListm, listDataChildm);
            // setting list adapter
            expListView.setAdapter(listAdapter);
            if(machinesClick == false){
                machines.setText("Refresh");
                workersClick = false;
                engineClick = false;
                machinesClick = true;
            }

        }else if(v.getId()==R.id.workers) {
            machines.setText("Machines");
            machinesClick = false;
            prepareListData();
            listAdapter = new Adapter(this, dataList, listDataChild);
            // setting list adapter
            expListView.setAdapter(listAdapter);
            if(workersClick == false){
                workers.setText("Refresh");
                machinesClick = false;
                engineClick = false;
                workersClick = true;
            }

        }else if(v.getId()==R.id.engine) {
            Toast.makeText(MapsActivity.this, "jeszcze nie ma", Toast.LENGTH_SHORT).show();
            chart();

        }
        }


    public void chart(){
        Intent i = new Intent(this, ChartIntent.class);
        startActivity(i);
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
/*
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
    }*/

    //ustawianie lokalizacji poczatkowej - lokalizacja sieci na urzadzeniu
    public void getLocation(){
        try{
            locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 10, 10*60*1, this);
            lat = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
            Log.i("poczatkowa lat", String.valueOf(lat));
            lon = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();
            Log.i("poczatkowa lon", String.valueOf(lon));

        }catch(Throwable e){
            e.printStackTrace();
        }

    }

   /*
   to bedzie funkcja do czyszczenia starych sensorow

   private void cleanOldSensors(final Double timeInHours){
        Thread t = new Thread(){
            @Override
            public void run() {
                while(networkConnection){
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Date date = new Date();
                    Timestamp twoMinutesAgo = new Timestamp(date.getTime() - (long)(timeInHours * HOUR*(1/60)));
                    for(Map.Entry<String,Timestamp> entry: listDataHeader.entrySet()){
                        String key = entry.getKey();
                        Timestamp timestamp = entry.getValue();
                        if(timestamp.before(twoMinutesAgo)){
                            listDataHeader.remove(key);
                            listDataChild.remove(key);
                            dataList.remove(key);
                        }
                    }
                }
            }
        };

        t.start();
    }
*/
}



