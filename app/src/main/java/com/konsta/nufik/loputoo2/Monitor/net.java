package com.konsta.nufik.loputoo2.Monitor;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Konstantin on 23-Feb-17.
 */

public class net extends Activity {

    String oke;
    String datum ;
    Double valuesy[] = {0.0,0.0,0.0,0.0,0.0};
    Double valuesy2[] = {0.0,0.0,0.0,0.0,0.0};
    TextView speed;
    TextView speed2;
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    AsyncTask MyTask= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net);
        speed = (TextView) findViewById(R.id.textView13);
        speed2 = (TextView) findViewById(R.id.textView14);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        mSeries1 = new LineGraphSeries<>();
        mSeries1.setColor(Color.parseColor("#FF26BE5B"));
        mSeries1.setThickness(12);

        graph.addSeries(mSeries1);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxY(1000.0);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setMaxX(4.0);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#2f9846"));
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#2f9846"));

        GraphView graph2 = (GraphView) findViewById(R.id.graph1);
        mSeries2 = new LineGraphSeries<>();
        mSeries2.setColor(Color.parseColor("#FF26BE5B"));
        mSeries2.setThickness(12);

        graph2.addSeries(mSeries2);
        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.getViewport().setMinY(0.0);
        graph2.getViewport().setMaxY(1000.0);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0.0);
        graph2.getViewport().setMaxX(4.0);
        graph2.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#2f9846"));
        graph2.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#2f9846"));

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("upload");
        spec.setContent(R.id.upload);
        spec.setIndicator("upload");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("download");
        spec.setContent(R.id.download);
        spec.setIndicator("download");
        host.addTab(spec);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop(){
        super.onStop();
        MyTask.cancel(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                MyTask = new MyTask().execute();
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer1, 1000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        super.onPause();
    }

    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {
            try{
                Socket socket = new Socket(MainActivity.host_ip, 1045);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
                data_out.writeByte(8);
                data_out.flush(); // Send off the data

                boolean done = false;
                while(!done) {
                    if (isCancelled()){
                        break;
                    }

                    byte messageType = data_in.readByte();
                    switch (messageType) {
                        case 1: // Type A
                            String end = "ok";
                            while (end.equals("ok")){
                                oke = data_in.readUTF();
                                final String oke2 = oke;
                                if (!oke2.equals("end")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // This code will always run on the UI thread, therefore is safe to modify UI elements.
                                            datum = oke2;
                                            String[] splitArray = new String[0];
                                            try {
                                                splitArray = datum.split("\\s+");
                                            } catch (PatternSyntaxException ex) {

                                            }
                                            Double datum1 = Double.parseDouble(splitArray[0]);
                                            Double datum2 = Double.parseDouble(splitArray[1]);

                                            int count = 5;
                                            int b = 0;

                                            for (int i=0; i<valuesy.length; i++) {
                                                b = b+1;
                                                if (i == 4){
                                                    valuesy[i] = datum1;
                                                }else {
                                                    valuesy[i] = valuesy[b];
                                                }
                                            }

                                            DataPoint[] values = new DataPoint[count];
                                            for (int i=0; i<count; i++) {
                                                double x = i;
                                                double y = valuesy[i];
                                                DataPoint v = new DataPoint(x, y);
                                                values[i] = v;
                                            }

                                            //Graph2
                                            int count2 = 5;
                                            int b2 = 0;

                                            for (int i=0; i<valuesy2.length; i++) {
                                                b2 = b2+1;
                                                if (i == 4){
                                                    valuesy2[i] = datum2;
                                                }else {
                                                    valuesy2[i] = valuesy2[b2];
                                                }
                                            }

                                            DataPoint[] values2 = new DataPoint[count2];
                                            for (int i=0; i<count2; i++) {
                                                double x = i;
                                                double y = valuesy2[i];
                                                DataPoint v = new DataPoint(x, y);
                                                values2[i] = v;
                                            }

                                            mSeries1.resetData(values);
                                            speed.setText(datum2.toString()+" kb/s");
                                            mSeries2.resetData(values2);
                                            speed2.setText(datum1.toString()+" kb/s");

                                        }
                                    });
                                }else{
                                    end = oke;
                                }
                            }
                            done = true;
                            break;
                        default:
                            done = true;

                    }
                }

                data_in.close();
                data_out.close();
                socket.close();
            }catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
}








