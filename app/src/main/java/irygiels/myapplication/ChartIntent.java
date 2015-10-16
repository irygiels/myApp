package irygiels.myapplication;

import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;


import com.google.android.gms.fitness.data.DataPoint;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class ChartIntent extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private LineGraphSeries<com.jjoe64.graphview.series.DataPoint> series;
    private int lastX=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_intent);
        GraphView graph = (GraphView)findViewById(R.id.graph);
        series = new LineGraphSeries<com.jjoe64.graphview.series.DataPoint>();
        graph.addSeries(series);
        Viewport viewport = graph.getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setYAxisBoundsManual(true);
        viewport.setMinX(0);
        viewport.setMaxX(20);
        viewport.setMinY(0);
        viewport.setMaxY(20);

        viewport.setScrollable(true);



    }


    private void addEntry(){

            series.appendData(new com.jjoe64.graphview.series.DataPoint(lastX++, RANDOM.nextDouble() * 20d),true, 100);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    try {
                        Thread.sleep(600);
                    } catch (Exception e) {
                    }

                }

            }

        }).start();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chart_intent, menu);
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
}
