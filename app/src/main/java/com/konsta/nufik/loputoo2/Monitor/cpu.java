package com.konsta.nufik.loputoo2.Monitor;

import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;

public class cpu extends Activity{
    String oke;
    ArrayList<String> datas = new ArrayList<String>();
    String datum1 ;
    String datum;
    String user_pr;
    String system_pr;
    String io_pr;

    TextView text1 ;
    TextView text2 ;
    TextView text3 ;
    TextView main;

    Double valuesy[] = {0.0,0.0,0.0,0.0,0.0};
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;
    AsyncTask MyTask= null;
    Boolean tehtud = false;

    Socket socket;
    DataOutputStream data_out ;
    DataInputStream data_in ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.cpu, container, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu);


        text1 =  (TextView) findViewById(R.id.textView7);
        text2 = (TextView) findViewById(R.id.textView8);
        text3 = (TextView) findViewById(R.id.textView9);
        main =  (TextView) findViewById(R.id.textView15);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        mSeries1 = new LineGraphSeries<>();

        mSeries1.setColor(Color.parseColor("#FF26BE5B"));
        mSeries1.setThickness(12);



        graph.addSeries(mSeries1);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxY(100.0);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setMaxX(4.0);

        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#2f9846"));
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#2f9846"));



        /*
        GraphView graph2 = (GraphView) findViewById(R.id.graph2);
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries(mSeries2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(5);
        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.getViewport().setMinY(0.0);
        graph2.getViewport().setMaxY(100.0);
*/
        //return rootView;
    }
/*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(MainActivity.ARG_SECTION_NUMBER));
    }
*/

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("okei9909090909");
        tehtud = true;


        MyTask.cancel(true);
        finish();
    }

    @Override
    public void onResume() {

        System.out.println("okei9909090909");



        mTimer1 = new Runnable() {
            @Override
            public void run() {
                if (!tehtud){

                    MyTask = new MyTask().execute();

                }else {


                }

                //MyTask.execute();

                //mSeries1.resetData(generateData());
                //mSeries1.resetData(new MyTask().execute());
                mHandler.postDelayed(this, 10);
            }
        };
        mHandler.postDelayed(mTimer1, 10);
        super.onResume();
        System.out.println("okei9909090909");

    }

    @Override
    public void onPause() {
        /*
        MyTask.cancel(true);
        finish();*/


        mHandler.removeCallbacks(mTimer1);

        super.onPause();
        //finish();
    }



    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... okkkk) {

            tehtud = true;


                try {

                    socket = new Socket(MainActivity.host_ip, 1045);
                    data_out = new DataOutputStream(socket.getOutputStream());
                    data_in = new DataInputStream(socket.getInputStream());
// Send first message
                    data_out.writeByte(6);
                    //data_out.writeUTF("This is the first type of message.");
                    data_out.flush(); // Send off the data

                    boolean done = false;
                    while (!done) {

                        byte messageType = data_in.readByte();

                        switch (messageType) {
                            case 1: // Type A
                                //System.out.println("Message A: " + data_in.readUTF());
                                String end = "ok";
                                while (end.equals("ok")) {
                                    oke = data_in.readUTF();
                                    final String oke2 = oke;
                                    if (!oke2.equals("end")) {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                // This code will always run on the UI thread, therefore is safe to modify UI elements.
                                                datum1 = oke2;

                                                    String[] splitArray = new String[0];

                                                        try {
                                                            splitArray = datum1.split("\\s+");

                                                        } catch (PatternSyntaxException ex) {
                                                            //
                                                        }

                                                        user_pr = splitArray[0];
                                                        system_pr = splitArray[1];
                                                        datum = Double.toString(100.0 - Math.round(Double.parseDouble(splitArray[2])));


                                                        io_pr = splitArray[3];

                                                text1.setText(user_pr + " %");
                                                text2.setText(system_pr+ " %");
                                                main.setText(datum.toString() + " %");
                                                text3.setText(io_pr+ " %");

                                                int count = 5;
                                                int b = 0;

                                                for (int i = 0; i < valuesy.length; i++) {
                                                    b = b + 1;
                                                    if (i == 4) {
                                                        valuesy[i] = Double.parseDouble(datum);
                                                    } else {
                                                        valuesy[i] = valuesy[b];
                                                    }
                                                }


                                                DataPoint[] values = new DataPoint[count];
                                                for (int i = 0; i < count; i++) {
                                                    double x = i;
                                                    double y = valuesy[i];
                                                    DataPoint v = new DataPoint(x, y);
                                                    values[i] = v;
                                                }

                                                mSeries1.resetData(values);


                                            }
                                        });
                                    } else {
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
                    tehtud = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;


        }


    }


}
























    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu);
        //itemname1.add("okk");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 3)
        });


        graph.addSeries(series);




        //new MyTask().execute();

        //System.out.println(itemname[0]);
        System.out.println("k");
        //System.out.println(items);

        //String[] itemss = i;

/*
        adapter=new CustomAdapterServices(this, itemname1);
        list=(ListView)findViewById(R.id.servicess);
        list.setAdapter(adapter);
//**************************************************************************************************

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem= itemname.get(+position);
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

*//*
    }

*/



