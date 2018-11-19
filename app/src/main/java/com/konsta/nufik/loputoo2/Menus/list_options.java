package com.konsta.nufik.loputoo2.Menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.konsta.nufik.loputoo2.R;
import com.konsta.nufik.loputoo2.Storage.Partition_table;

/**
 * Created by Konstantin on 11/8/2016.
 */

public class list_options extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_options2);
    }

    public void onClick1(View view){
        Intent i9 = new Intent(list_options.this, services2.class);
        startActivity(i9);
    }

    public void onClick2(View view){
        Intent i9 = new Intent(list_options.this, Partition_table.class);
        startActivity(i9);
    }

    public void onClick3(View view){
        Intent i9 = new Intent(list_options.this, monitoring.class);
        startActivity(i9);
    }

    public void onClick4(View view){
        Intent i9 = new Intent(list_options.this, operate.class);
        startActivity(i9);
    }




}
