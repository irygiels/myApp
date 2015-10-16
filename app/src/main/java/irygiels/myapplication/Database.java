package irygiels.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Blob;

/**
 * Created by irygiels on 24.09.15.
 */
public class Database extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "baza";

        private static final String PRACOWNIK = "Pracownik";
        private static final String GRUPA_ROBOCZA = "GrupaRobocza";
        private static final String AKCELEROMETR = "Akcelerometr";
        private static final String AKTYWNY = "Aktywny";
        private static final String BEACON = "Beacon";
        private static final String GPS = "GPS";
        private static final String HELLO = "Hello";
        private static final String KONTENER = "Kontener";
        private static final String OBD = "OBD";
        private static final String PARAMETRY = "Parametry";
        private static final String POJAZD = "Pojazd";
        private static final String RFID = "RFID";
        private static final String PRZEDMIOT = "Przedmiot";
        public static final String tag_idPracownik="idPracownik";
        public static final String tag_typ="typ";
        public static final String tag_imie="imie";
        public static final String tag_nazwisko="nazwisko";
        public static final String tag_wiek="wiek";
        public static final String tag_wzrost="wzrost";
        public static final Blob tag_zdjecie = null;
        public static final String tag_grupa_id="grupa_id";
        public static final String tag_tag_id="tag_id";
        public static final String tag_idGrupa="idGrupa";
        public static final String tag_nazwa="nazwa";
        public static final String tag_pojazd_id="pojazd_id";
        public static final String tag_idPojazd="idPojazd";
        public static final String tag_vin="vin";
        public static final String tag_id="id";
        public static final String tag_idPrzedmiot="idPrzedmiot";
        public static final String tag_przedmiot_id="przedmiot_id";
        public static final String tag_producent="producent";
        public static final String tag_rokProdukcji="rokProdukcji";
        public static final String tag_idKontener="idKontener";
        public static final String tag_mac_id="mac_id";
        public static final String tag_idOBD="idOBD";
        public static final String tag_idBeacon="Beacon";
        public static final String tag_odleglosc="odleglosc";
        public static final String tag_id_tag="id_tag";
        public static final String tag_batteryLevel="batteryLevel";
        public static final String tag_idParametry="idParametry";
        public static final String tag_paliwo="paliwo";
        public static final String tag_temp_oleju="temp_oleju";
        public static final String tag_predkosc="predkosc";
        public static final String tag_temp_cieczy_chlodniczej="temp_cieczy_chlodniczej";
        public static final String tag_OBD_id="OBD_id";
        public static final String tag_timestamp="timestamp";
        public static final String tag_idHello="idHello";
        public static final String tag_hello="hello";
        public static final String tag_data="data";
        public static final String tag_RSSI="RSSI";
        public static final String tag_accel_x="accel_x";
        public static final String tag_accel_y="accel_y";
        public static final String tag_accel_z="accel_z";
        public static final String tag_idRFID="idRFID";
        public static final String tag_adresCzytnika="adresCzytnika";
        public static final String tag_idGeolokalizacja="idGeolokalizacja";
        public static final String tag_latitude="latitude";
        public static final String tag_longitude="longitude";
        public static final String tag_wysokosc="wysokosc";



    public Database(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // SQL statements to create tables
            String CREATE_PRACOWNIK_TABLE = "CREATE TABLE Pracownik ( " +
                    "idPracownik INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "typ TEXT, "+
                    "imie TEXT, "+
                    "nazwisko TEXT, "+
                    "wiek TEXT, "+
                    "wzrost TEXT, "+
                   // "zdjecie BLOB" +
                    "grupa_id INTEGER" +
                    "tag_id INTEGER )";

            String CREATE_GRUPA_ROBOCZA_TABLE = "CREATE TABLE GrupaRobocza ( " +
                    "idGrupa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nazwa TEXT, "+
                    "pojazd_id INTEGER )";

            String CREATE_POJAZD_TABLE = "CREATE TABLE Pojazd ( " +
                    "idPojazd INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "vin TEXT, "+
                    "nazwa TEXT )";

            String CREATE_PRZEDMIOT_TABLE = "CREATE TABLE Przedmiot ( " +
                    "idPrzedmiot INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "typ TEXT, "+
                    "nazwa TEXT, "+
                    "producent TEXT, "+
                    "rokProdukcji TEXT, "+
                    "pojazd_id INTEGER )";

            String CREATE_KONTENER_TABLE = "CREATE TABLE Kontener ( " +
                    "idKontener INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "typ TEXT, "+
                    "producent TEXT, "+
                    "przedmiot_id INTEGER )";

            String CREATE_OBD_TABLE = "CREATE TABLE OBD ( " +
                    "idOBD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "mac_id TEXT, "+
                    "pojazd_id INTEGER, "+
                    "przedmiot_id INTEGER )";

            String CREATE_BEACON_TABLE = "CREATE TABLE Beacon ( " +
                    "idBeacon INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "mac_id TEXT, "+
                    "przedmiot_id INTEGER, "+
                    "odleglosc TEXT )";

            String CREATE_AKTYWNY_TABLE = "CREATE TABLE TagAktywny ( " +
                    "idTag INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "przedmiot_id INTEGER, "+
                    "mac_id TEXT, "+
                    "batteryLevel INTEGER )";

            String CREATE_PARAMETRY_TABLE = "CREATE TABLE ParametryPojazdu ( " +
                    "idParametry INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "paliwo INTEGER, "+
                    "temp_oleju INTEGER, "+
                    "temp_cieczy_chlodniczej INTEGER, "+
                    "predkosc INTEGER, "+
                    "OBD_id INTEGER, "+
                    "timestamp TEXT )";

            String CREATE_HELLO_TABLE = "CREATE TABLE Hello ( " +
                    "idHello INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hello TEXT, "+
                    "timestamp TEXT, "+
                    "mac_id TEXT, "+
                    "tag_id INTEGER, "+
                    "odleglosc INTEGER, "+
                    "RSSI INTEGER, "+
                    "OBD_id INTEGER )";

            String CREATE_AKCELEROMETR_TABLE = "CREATE TABLE Akcelerometr ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "data TEXT, "+
                    "timestamp TEXT, "+
                    "accel_x TEXT, "+
                    "accel_y TEXT, "+
                    "accel_z TEXT, "+
                    "mac_id TEXT, "+
                    "tag_id INTEGER )";

            String CREATE_RFID_TABLE = "CREATE TABLE RFID ( " +
                    "idRFID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "przedmiot_id INT, "+
                    "mac_id TEXT, "+
                    "adresCzytnika TEXT) ";

            String CREATE_GPS_TABLE = "CREATE TABLE GPS ( " +
                    "idGeolokalizacja INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "latitude TEXT, "+
                    "longitude TEXT, "+
                    "wysokosc TEXT, "+
                    "timestamp TEXT, "+
                    "predkosc INTEGER, "+
                    "pojazd_id INTEGER )";

            // create tables
            db.execSQL(CREATE_AKTYWNY_TABLE);
            db.execSQL(CREATE_BEACON_TABLE);
            db.execSQL(CREATE_GPS_TABLE);
            db.execSQL(CREATE_GRUPA_ROBOCZA_TABLE);
            db.execSQL(CREATE_HELLO_TABLE);
            db.execSQL(CREATE_KONTENER_TABLE);
            db.execSQL(CREATE_OBD_TABLE);
            db.execSQL(CREATE_PARAMETRY_TABLE);
            db.execSQL(CREATE_POJAZD_TABLE);
            db.execSQL(CREATE_PRZEDMIOT_TABLE);
            db.execSQL(CREATE_PRACOWNIK_TABLE);
            db.execSQL(CREATE_AKCELEROMETR_TABLE);
            db.execSQL(CREATE_RFID_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older books table if existed
            db.execSQL("DROP TABLE IF EXISTS Pracownik, GrupaRobocza, Pojazd, " +
                    "Przedmiot, Kontener, OBD, Beacon, TagAktywny, ParametryPojazdu, " +
                    "Hello, Akcelerometr, RFID, GPS");

            // create fresh tables
            this.onCreate(db);
        }



    public void insertData(String typ, String imie, String nazwisko, String wiek, String wzrost, int grupa_id, int tag_id){

       /* String CREATE_PRACOWNIK_TABLE = "CREATE TABLE Pracownik ( " +
                "idPracownik INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "typ TEXT, "+
                "imie TEXT, "+
                "nazwisko TEXT, "+
                "wiek TEXT, "+
                "wzrost TEXT, "+
                "zdjecie BLOB" +
                "grupa_id INTEGER" +
                "tag_id INTEGER )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_typ, typ);
        values.put(tag_imie, imie);
        values.put(tag_nazwisko, nazwisko);
        values.put(tag_wiek, wiek);
        values.put(tag_wzrost, wzrost);
        //values.put(zdjecie, tag_zdjecie);
        values.put(tag_grupa_id, grupa_id);
        values.put(tag_tag_id, tag_id);
        db.insert(PRACOWNIK, null, values);

    }

    public void insertData(String nazwa, int pojazd_id){

       /*             String CREATE_GRUPA_ROBOCZA_TABLE = "CREATE TABLE GrupaRobocza ( " +
                    "idGrupa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nazwa TEXT, "+
                    "pojazd_id INTEGER )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_nazwa, nazwa);
        values.put(tag_pojazd_id, pojazd_id);
        db.insert(GRUPA_ROBOCZA, null, values);

    }

    public void insertData(String vin, String nazwa){

       /*
            String CREATE_POJAZD_TABLE = "CREATE TABLE Pojazd ( " +
                    "idPojazd INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "vin TEXT, "+
                    "nazwa TEXT )";
         */

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_vin, vin);
        values.put(tag_nazwa, nazwa);

        db.insert(POJAZD, null, values);

    }

    public void insertData(String typ, String nazwa, String producent, String rokProdukcji, int pojazd_id){

       /*  String CREATE_PRZEDMIOT_TABLE = "CREATE TABLE Przedmiot ( " +
                    "idPrzedmiot INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "typ TEXT, "+
                    "nazwa TEXT, "+
                    "producent TEXT, "+
                    "rokProdukcji TEXT, "+
                    "pojazd_id INTEGER )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_typ, typ);
        values.put(tag_nazwa, nazwa);
        values.put(tag_producent, producent);
        values.put(tag_rokProdukcji, rokProdukcji);
        values.put(tag_pojazd_id, pojazd_id);

        db.insert(PRZEDMIOT, null, values);

    }

    public void insertData(String typ, String producent, int przedmiot_id){

       /*
                   String CREATE_KONTENER_TABLE = "CREATE TABLE Kontener ( " +
                    "idKontener INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "typ TEXT, "+
                    "producent TEXT, "+
                    "przedmiot_id INTEGER )";
    */

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_typ, typ);
        values.put(tag_producent, producent);
        values.put(tag_przedmiot_id, przedmiot_id);

        db.insert(KONTENER, null, values);

    }

    public void insertData(String mac_id, int pojazd_id, int przedmiot_id){

       /* String CREATE_OBD_TABLE = "CREATE TABLE OBD ( " +
                    "idOBD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "mac_id TEXT, "+
                    "pojazd_id INTEGER, "+
                    "przedmiot_id INTEGER )";
        */

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_mac_id, mac_id);
        values.put(tag_pojazd_id, pojazd_id);
        values.put(tag_przedmiot_id, przedmiot_id);

        db.insert(OBD, null, values);

    }

    public void insertData(String mac_id, int przedmiot_id, String odleglosc){

       /*
            String CREATE_BEACON_TABLE = "CREATE TABLE Beacon ( " +
                    "idBeacon INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "mac_id TEXT, "+
                    "przedmiot_id INTEGER, "+
                    "odleglosc TEXT )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_mac_id, mac_id);
        values.put(tag_przedmiot_id, przedmiot_id);
        values.put(tag_odleglosc, odleglosc);

        db.insert(BEACON, null, values);

    }

    public void insertData(int przedmiot_id, String mac_id, int batteryLevel){

       /* String CREATE_AKTYWNY_TABLE = "CREATE TABLE TagAktywny ( " +
                    "idTag INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "przedmiot_id INTEGER, "+
                    "mac_id TEXT, "+
                    "batteryLevel INTEGER )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_mac_id, mac_id);
        values.put(tag_przedmiot_id, przedmiot_id);
        values.put(tag_batteryLevel, batteryLevel);

        db.insert(AKTYWNY, null, values);

    }

    public void insertData(int paliwo, int temp_oleju, int temp_cieczy_chlodniczej, int predkosc, int OBD_id, String timestamp){

       /*             String CREATE_PARAMETRY_TABLE = "CREATE TABLE ParametryPojazdu ( " +
                    "idParametry INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "paliwo INTEGER, "+
                    "temp_oleju INTEGER, "+
                    "temp_cieczy_chlodniczej INTEGER, "+
                    "predkosc INTEGER, "+
                    "OBD_id INTEGER, "+
                    "timestamp TEXT )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_paliwo, paliwo);
        values.put(tag_temp_oleju, temp_oleju);
        values.put(tag_temp_cieczy_chlodniczej, temp_cieczy_chlodniczej);
        values.put(tag_predkosc, predkosc);
        values.put(tag_timestamp, timestamp);
        values.put(tag_OBD_id, OBD_id);

        db.insert(PARAMETRY, null, values);

    }

    public void insertData(String hello, String timestamp, String mac_id, int tag_id, int odleglosc, int RSSI, int OBD_id){

       /*
            String CREATE_HELLO_TABLE = "CREATE TABLE Hello ( " +
                    "idHello INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hello TEXT, "+
                    "timestamp TEXT, "+
                    "mac_id TEXT, "+
                    "tag_id INTEGER, "+
                    "odleglosc INTEGER, "+
                    "RSSI INTEGER, "+
                    "OBD_id INTEGER )";
*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_hello, hello);
        values.put(tag_timestamp, timestamp);
        values.put(tag_mac_id, mac_id);
        values.put(tag_tag_id, tag_id);
        values.put(tag_odleglosc, odleglosc);
        values.put(tag_RSSI, RSSI);
        values.put(tag_OBD_id, OBD_id);

        db.insert(HELLO, null, values);

    }

    public void insertData(String data, String timestamp, String accel_x, String accel_y, String accel_z, String mac_id, int tag_id){

       /*  String CREATE_AKCELEROMETR_TABLE = "CREATE TABLE Akcelerometr ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "data TEXT, "+
                    "timestamp TEXT, "+
                    "accel_x TEXT, "+
                    "accel_y TEXT, "+
                    "accel_z TEXT, "+
                    "mac_id TEXT, "+
                    "tag_id INTEGER )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_data, data);
        values.put(tag_timestamp, timestamp);
        values.put(tag_accel_x, accel_x);
        values.put(tag_accel_y, accel_y);
        values.put(tag_accel_z, accel_z);
        values.put(tag_mac_id, mac_id);
        values.put(tag_tag_id, tag_id);

        db.insert(AKCELEROMETR, null, values);

    }

    public void insertData(int przedmiot_id, String mac_id, String adresCzytnika){

       /* String CREATE_RFID_TABLE = "CREATE TABLE RFID ( " +
                    "idRFID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "przedmiot_id INT, "+
                    "mac_id TEXT, "+
                    "adresCzytnika TEXT) ";
*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_mac_id, mac_id);
        values.put(tag_adresCzytnika, adresCzytnika);
        values.put(tag_przedmiot_id, przedmiot_id);

        db.insert(RFID, null, values);

    }

    public void insertData(String latitude, String longitude, String wysokosc, String timestamp, int predkosc, int pojazd_id){

       /* String CREATE_GPS_TABLE = "CREATE TABLE GPS ( " +
                    "idGeolokalizacja INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "latitude TEXT, "+
                    "longitude TEXT, "+
                    "wysokosc TEXT, "+
                    "timestamp TEXT, "+
                    "predkosc INTEGER, "+
                    "pojazd_id INTEGER )";*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_latitude, latitude);
        values.put(tag_longitude, longitude);
        values.put(tag_wysokosc, wysokosc);
        values.put(tag_timestamp, timestamp);
        values.put(tag_predkosc, predkosc);
        values.put(tag_pojazd_id, pojazd_id);

        db.insert(GPS, null, values);

    }

    //Cursor dla każdej tabeli

    public Cursor getData(String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_name, null);
        return res;
    }

    /*


        // close
        db.close();
    }

    public Sensor getSensor(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_SENSOR, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit


    }*/

}

