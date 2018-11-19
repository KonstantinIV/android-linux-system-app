package com.konsta.nufik.loputoo2.Menus;

import com.konsta.nufik.loputoo2.Monitor.cpu;
import com.konsta.nufik.loputoo2.Monitor.net;
import com.konsta.nufik.loputoo2.Monitor.ram;
import com.konsta.nufik.loputoo2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Konstantin on 10-Mar-17.
 */

public class monitoring extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitoring);
    }

    public void cpu_click(View view){
        Intent i9 = new Intent(monitoring.this, cpu.class);
        startActivity(i9);
    }

    public void network_click(View view){
        Intent i10 = new Intent(monitoring.this, net.class);
        startActivity(i10);
    }

    public void ram_click(View view){
        Intent i10 = new Intent(monitoring.this, ram.class);
        startActivity(i10);
    }

}
