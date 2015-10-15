package irygiels.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by irygiels on 24.09.15.
 */
public class Database extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "baza";
        public static final String tag_accel_x="accel_x";
        public static final String tag_accel_y="accel_y";
        public static final String tag_accel_z="accel_z";
        public static final String tag_mac_id="mac_id";
        public static final String tag_id="id";
        public static final String TABLE_NAME = "sensor";


    public Database(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // SQL statement to create book table
            String CREATE_SENSOR_TABLE = "CREATE TABLE sensor ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "accel_x TEXT, "+
                    "accel_y TEXT, "+
                    "accel_z TEXT, "+
                    "mac_id TEXT )";

            // create sensor table
            db.execSQL(CREATE_SENSOR_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older books table if existed
            db.execSQL("DROP TABLE IF EXISTS sensor");

            // create fresh books table
            this.onCreate(db);
        }


    //do tego momentu mam pusta tabelke z wartosciami sensora

    public boolean insertData(String accel_x, String accel_y, String accel_z, String mac_id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tag_accel_x, accel_x); // get accel_x
        values.put(tag_accel_y, accel_y); // get accel_x
        values.put(tag_accel_z, accel_z); // get accel_x
        values.put(tag_mac_id, mac_id); // get mac_id

        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1){
            return false;
        }else{
            return true;
        }
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

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Sensor sensor = new Sensor();
        sensor.setId(Integer.parseInt(cursor.getString(0)));
        sensor.setAccel_x(cursor.getString(1));
        sensor.setAccel_y(cursor.getString(2));
        sensor.setAccel_z(cursor.getString(3));
        sensor.setMac_id(cursor.getString(4));


        //log
        Log.d("getSensor(" + id + ")", sensor.toString());

        // 5. return book
        return sensor;
    }*/

}

