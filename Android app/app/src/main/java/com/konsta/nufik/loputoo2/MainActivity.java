package com.konsta.nufik.loputoo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.konsta.nufik.loputoo2.Menus.list_options;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends Activity {
    TextView Tekst;
    TextView Tekst2;
    public static int ss = 1045;
    public static InetAddress host_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tekst = (TextView) findViewById(R.id.textView);
        Tekst2 = (EditText) findViewById(R.id.editText);
    }

    public void listOptions(View view) {
        if(view.getId() == R.id.button3) {
            String ip = Tekst2.getEditableText().toString();
            try {
                host_ip  = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(MainActivity.this, list_options.class);
            startActivity(i);
        }

    }

}


