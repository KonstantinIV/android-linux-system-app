package com.konsta.nufik.loputoo2.Menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.konsta.nufik.loputoo2.R;
import com.konsta.nufik.loputoo2.Service.open_ports;
import com.konsta.nufik.loputoo2.Service.services;

/**
 * Created by Konstantin on 10-Mar-17.
 */

public class services2 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services2);
    }

    public void services_click(View view){
        Intent i9 = new Intent(services2.this, services.class);
        startActivity(i9);
    }

    public void ports_click(View view){
        Intent i10 = new Intent(services2.this, open_ports.class);
        startActivity(i10);
    }
}
