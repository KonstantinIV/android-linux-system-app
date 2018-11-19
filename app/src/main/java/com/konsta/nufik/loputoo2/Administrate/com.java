package com.konsta.nufik.loputoo2.Administrate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.konsta.nufik.loputoo2.MainActivity;
import com.konsta.nufik.loputoo2.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;



/**
 * Created by Konstantin on 24-Feb-17.
 */

public class com extends Activity {
    String oke;
    String command;
    TextView text;
    TextView status;
    String datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com);
        text = (EditText) findViewById(R.id.editText2);
        status = (TextView) findViewById(R.id.textView17);
    }

    public void sendCommand(View view) {
        command = text.getEditableText().toString();
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... ok) {
            try{
                Socket socket = new Socket(MainActivity.host_ip, 1045);
                DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
                DataInputStream data_in = new DataInputStream(socket.getInputStream());
                data_out.writeByte(9);
                data_out.flush();

                boolean done = false;
                while(!done) {
                    byte messageType = data_in.readByte();
                    switch (messageType) {
                        case 1:
                            String end = "ok";
                            data_out.writeUTF(command);
                            while (end.equals("ok")){
                                oke = data_in.readUTF();
                                final String oke2 = oke;
                                if (!oke2.equals("end")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // This code will always run on the UI thread, therefore is safe to modify UI elements.
                                            datum = oke2;
                                        }
                                    });
                                }else{
                                    end = oke;
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // This code will always run on the UI thread, therefore is safe to modify UI elements.
                                    status.setText(datum);
                                }
                            });
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
