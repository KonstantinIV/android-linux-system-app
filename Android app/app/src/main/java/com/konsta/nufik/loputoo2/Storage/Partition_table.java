package com.konsta.nufik.loputoo2.Storage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.konsta.nufik.loputoo2.CustomLists.CustomAdapterPartitionTable;
import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Konstantin on 08-Jan-17.
 */

public class Partition_table extends Activity {

    ListView list;
    String oke;
    List<String> itemname = new ArrayList<String>();
    List<String> itemname1 = new ArrayList<String>();
    List<String> itemname2 = new ArrayList<String>();
    List<String> itemname3 = new ArrayList<String>();
    List<String> itemname4 = new ArrayList<String>();

    List<String> itemname5 = new ArrayList<String>();

    List<String> items = new ArrayList<String>();
    List<String> harddrives = new ArrayList<String>();
    CustomAdapterPartitionTable adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partition_table);
        new MyTask().execute();

        adapter=new CustomAdapterPartitionTable(this, itemname, itemname1, itemname2, itemname3, itemname4);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {
            try{
                Socket socket = new Socket(MainActivity.host_ip, 1045);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
                data_out.writeByte(3);
                data_out.flush(); // Send off the data

                boolean done = false;
                while(!done) {
                    byte messageType = data_in.readByte();

                    switch (messageType) {
                        case 1:
                            String end = "ok";
                            while (end.equals("ok")){
                                oke = data_in.readUTF();
                                final String oke2 = oke;
                                if (!oke2.equals("end")) {
                                    harddrives.add(oke2);
                                }else{
                                    end = oke;
                                }
                            }
                            String end2 = "ok";
                            while (end2.equals("ok")){
                                oke = data_in.readUTF();
                                final String oke23 = oke;
                                if (!oke23.equals("end")) {
                                    items.add(oke23);
                                }else{
                                    end2 = oke;
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
                int x = 0;
                for(String u : items) {
                    String[] splitArray = new String[0];
                        try {
                            splitArray = u.split("\\s+");
                        } catch (PatternSyntaxException ex) {
                        }
                        itemname.add(splitArray[0]);
                        itemname1.add(splitArray[1]);
                        itemname2.add(splitArray[2]);
                    if(splitArray.length == 4){
                        itemname3.add(splitArray[3]);
                    }else {
                        itemname3.add(" ");
                    }

//Uus
                    for(String s : harddrives) {
                        if (s.equals(splitArray[0])){
                            itemname4.add("1");
                            break;
                        }
                    }
                    if (itemname4.size() == x){
                        itemname4.add("0");
                    }



                   /* if (itemname3.get(x).equals(" ") || itemname3.get(x).equals("[SWAP]")){


                        itemname5.add(" ");


                       }else{


                        socket = new Socket(MainActivity.host_ip, 1045);
                        data_out = new DataOutputStream(socket.getOutputStream());
                        data_in = new DataInputStream(socket.getInputStream());

                        data_out.writeByte(13);
                        data_out.flush(); // Send off the data

                        boolean done5 = false;
                        while(!done5) {





                            String end = "ok";
                            while (end.equals("ok")){
                                data_out.writeUTF("df --output=used /dev/"+itemname.get(x)+" | tail -1");
                                oke = data_in.readUTF();
                                System.out.println(oke + "222222222222222");
                                final String oke2 = oke;
                                if (!oke2.equals("end")) {
                                    itemname5.add(oke2);
                                }else{
                                    end = oke;
                                }
                            }

                            done5 = true;



                        }


                    }*/



                    System.out.println(itemname5);


                    x = x +1;
                }



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

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

