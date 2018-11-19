package com.konsta.nufik.loputoo2.Service;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.konsta.nufik.loputoo2.CustomLists.CustomAdapterServices;
import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Konstantin on 15-Jan-17.
 */

public class services extends Activity  {
    ListView list;
    String oke;
    CustomAdapterServices adapter;
    List<String> itemname1 = new ArrayList<String>();
    public static List<String> itemname2 = new ArrayList<String>();
    List<String> items = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        new MyTask().execute();
        adapter=new CustomAdapterServices(this, itemname1);
        list=(ListView)findViewById(R.id.servicess);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem= itemname.get(+position);
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {
            itemname1.clear();
            itemname2.clear();
            try {
                Socket socket = new Socket(MainActivity.host_ip, MainActivity.ss);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
                data_out.writeByte(5);
                data_out.flush(); // Send off the data

                boolean done = false;
                while (!done) {
                    byte messageType = data_in.readByte();
                    switch (messageType) {
                        case 1:
                            String end = "ok";
                            while (end.equals("ok")) {
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

                for (String u : items) {
                    String[] splitArray = new String[0];
                        try {
                            splitArray = u.split("\\s+");
                        } catch (PatternSyntaxException ex) {
                        }
                        itemname2.add(splitArray[0]);
                        itemname1.add(splitArray[1]);
                    }
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
