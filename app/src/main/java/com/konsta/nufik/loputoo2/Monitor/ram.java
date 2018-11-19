package com.konsta.nufik.loputoo2.Monitor;

import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Konstantin on 23-Feb-17.
 */

public class ram extends Activity {
    String oke;
    String datum ;
    Double valuesy[] = {0.0,0.0,0.0,0.0,0.0};
    TextView ram_sp;

    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private LineGraphSeries<DataPoint> mSeries1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ram);
        ram_sp =  (TextView) findViewById(R.id.textView16);
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

    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                new MyTask().execute();
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
                data_out.writeByte(7);
                data_out.flush(); // Send off the data

                boolean done = false;
                System.out.println("Done");
                while(!done) {
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
                                            int count = 5;
                                            int b = 0;
                                            for (int i=0; i<valuesy.length; i++) {
                                                b = b+1;
                                                if (i == 4){
                                                    valuesy[i] = Double.parseDouble(datum);
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
                                            ram_sp.setText(datum + " MB");
                                            mSeries1.resetData(values);
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