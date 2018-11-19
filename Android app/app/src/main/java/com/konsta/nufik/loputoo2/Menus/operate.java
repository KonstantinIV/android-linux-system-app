package com.konsta.nufik.loputoo2.Menus;

import com.konsta.nufik.loputoo2.Administrate.com;
import com.konsta.nufik.loputoo2.Administrate.scripts;
import com.konsta.nufik.loputoo2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Konstantin on 10-Mar-17.
 */

public class operate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operate);
    }

    public void command_click(View view){
        Intent i9 = new Intent(operate.this, com.class);
        startActivity(i9);
    }

    public void scripts_click(View view){
        Intent i10 = new Intent(operate.this, scripts.class);
        startActivity(i10);
    }
}
