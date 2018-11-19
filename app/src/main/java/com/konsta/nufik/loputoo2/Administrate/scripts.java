package com.konsta.nufik.loputoo2.Administrate;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import com.konsta.nufik.loputoo2.R;
import com.konsta.nufik.loputoo2.MainActivity;
/**
 * Created by Konstantin on 28-Feb-17.
 */

public class scripts extends Activity {


    ListView list;
    String oke;
    String main_string;
    //CustomAdapterServices adapter;

    List<String> words = new ArrayList<String>();

    ArrayAdapter adapter;
    String valitud;



    List<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scripts);


        new MyTask().execute();
        adapter = new ArrayAdapter<String>(this, R.layout.textview_0,
                items);

        list = (ListView) findViewById(R.id.scriptss);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                valitud = String.valueOf(adapterView.getItemAtPosition(position));
                String picked = "You selected " + String.valueOf(adapterView.getItemAtPosition(position));
                System.out.println(picked);
                new MyTask2().execute();

                Toast.makeText(scripts.this, picked, Toast.LENGTH_SHORT).show();

            }
        });
    }

    class MyTask2 extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {

            try {
                Socket socket = new Socket(MainActivity.host_ip, 1045);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
// Send first message
                data_out.writeByte(11);
                //data_out.writeUTF("This is the first type of message.");
                data_out.flush(); // Send off the data

                boolean done = false;

                while (!done) {
                    byte messageType = data_in.readByte();

                    switch (messageType) {
                        case 1: // Type A
                            //System.out.println("Message A: " + data_in.readUTF());
                            data_out.writeUTF(valitud);
                            String end = "ok";
                            while (end.equals("ok")) {
                                //System.out.println("Message A:");

                                oke = data_in.readUTF();
                                final String oke2 = oke;
                                System.out.println(oke2);


                                if (!oke2.equals("end")) {

                                    System.out.println(oke2);
                                } else {
                                    end = oke;
                                }

                            }

                            //System.out.println("Message A:");
                            done = true;

                            break;


                        default:
                            done = true;

                    }
                }
                data_in.close();
                data_out.close();
                socket.close();


            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }





    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {

            try {

                Socket socket = new Socket(MainActivity.host_ip, 1045);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
// Send first message
                data_out.writeByte(10);
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
                                //System.out.println("Message A:");

                                oke = data_in.readUTF();
                                final String oke2 = oke;


                                if (!oke2.equals("end")) {

                                    items.add(oke2);
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


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //list.invalidate();
                        adapter.notifyDataSetChanged();

                    }
                });

            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }






}
