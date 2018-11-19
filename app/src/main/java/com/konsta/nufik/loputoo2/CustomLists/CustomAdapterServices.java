package com.konsta.nufik.loputoo2.CustomLists;

    import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;
import com.konsta.nufik.loputoo2.Service.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by Konstantin on 15-Jan-17.
 */

public class CustomAdapterServices extends ArrayAdapter<String> {
    Button button1;
    private final Activity context;
    private final List<String> itemname1;
    CharSequence service;
    int pos;

    public CustomAdapterServices(Activity context, List<String> itemname1) {
        super(context, R.layout.mylist5xml, itemname1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname1=itemname1;
    }

    public View getView(final int position, final View view, final ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.mylist5xml, null, true);

        if (services.itemname2.get(position).equals("+")) {
            rowView.setBackgroundResource(R.drawable.row);
        }else if(services.itemname2.get(position).equals("-")){
            rowView.setBackgroundResource(R.drawable.row2);
        }

        button1 = (Button) rowView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context,v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        service = item.getTitle();
                        pos = position;

                        if (service.equals("Add to custom list")){


                        }else{
                            new MyTask().execute();
                        }

                        Toast.makeText(context, "You Clicked : " + itemname1.get(pos) + service,
                                Toast.LENGTH_SHORT
                        ).show();

                        return true;
                    }
                });
                popup.inflate(R.menu.popup_menu);
                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        extratxt.setText(itemname1.get(position));
        return rowView;
    }

class MyTask extends AsyncTask<String, Void, Void> {
    protected Void doInBackground(String... ok) {

        try {
            System.out.println(MainActivity.host_ip);

            Socket socket = new Socket(MainActivity.host_ip, MainActivity.ss);
            DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
            DataInputStream data_in = new DataInputStream(socket.getInputStream());
            data_out.writeByte(12);
            data_out.flush(); // Send off the data
            boolean done = false;
            while (!done) {
                byte messageType = data_in.readByte();
                switch (messageType) {
                    case 1:
                        if (service.equals("Start service")) {
                            data_out.writeByte(1);
                            data_out.writeUTF(itemname1.get(pos));
                            data_out.flush();
                            done = true;
                            break;

                        } else if (service.equals("Stop service")) {
                            data_out.writeByte(2);
                            data_out.writeUTF(itemname1.get(pos));
                            data_out.flush();
                            done = true;
                            break;

                        } else if (service.equals("Restart service")) {
                            data_out.writeByte(3);
                            data_out.writeUTF(itemname1.get(pos));
                            data_out.flush();
                            done = true;
                            break;
                        }
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
}