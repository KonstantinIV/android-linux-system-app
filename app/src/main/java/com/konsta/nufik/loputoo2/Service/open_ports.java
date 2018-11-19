package com.konsta.nufik.loputoo2.Service;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.konsta.nufik.loputoo2.CustomLists.CustomAdapterOpenPorts;
import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;


/**
 * Created by Konstantin on 14-Jan-17.
 */

public class open_ports extends Activity {
    ListView list;
    String oke;
    List<String> itemname = new ArrayList<String>();
    List<String> itemname1 = new ArrayList<String>();
    List<String> itemname2 = new ArrayList<String>();
    CustomAdapterOpenPorts adapter;
    List<String> tcp = new ArrayList<>();
    List<String> udp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_ports);
        new MyTask().execute();
        adapter=new CustomAdapterOpenPorts(this, itemname, itemname1, itemname2);
        list=(ListView)findViewById(R.id.open_ports);
        list.setAdapter(adapter);
    }

    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {
            try{
                Socket socket = new Socket(MainActivity.host_ip, 1045);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
                data_out.writeByte(4);
                data_out.flush(); // Send off the data

                boolean done = false;

                while(!done) {
                    byte messageType = data_in.readByte();
                    switch (messageType) {
                        case 1:
                            String end = "ok";
                            String type = null;
                            String oke2 = "";
                            while (end.equals("ok")){
                                oke = data_in.readUTF();
                                if (oke.equals("tcp")){
                                    type = oke;
                                    continue;
                                }else if (oke.equals("udp")){
                                    type = oke;
                                    continue;
                                }else {
                                    oke2 = oke;
                                }

                                if (!oke2.equals("end")) {  
                                    if (type.equals("tcp")){
                                        tcp.add(oke2);
                                    }else if(type.equals("udp")){
                                        udp.add(oke2);
                                    }
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
                    for(String u : tcp) {
                    String[] splitArray = new String[0];
                    if (!(u.equals("udp")) || !(u.equals("tcp"))) {
                        try {
                            splitArray = u.split("\\s+");
                        } catch (PatternSyntaxException ex) {
                        }
                        itemname.add(splitArray[0]);
                        itemname1.add(splitArray[1]);
                        itemname2.add("tcp");
                    }
                }
                for(String u : udp) {
                    String[] splitArray = new String[0];
                    if (!(u.equals("udp")) || !(u.equals("tcp"))) {
                        try {
                            splitArray = u.split("\\s+");
                        } catch (PatternSyntaxException ex) {
                        }
                        itemname.add(splitArray[0]);
                        itemname1.add(splitArray[1]);
                        itemname2.add("udp");
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
}






