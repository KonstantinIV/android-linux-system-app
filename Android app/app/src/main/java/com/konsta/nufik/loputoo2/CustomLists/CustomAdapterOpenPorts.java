package com.konsta.nufik.loputoo2.CustomLists;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.konsta.nufik.loputoo2.R;


import java.util.List;

/**
 * Created by Konstantin on 14-Jan-17.
 */

public class CustomAdapterOpenPorts extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> itemname;
    private final List<String> itemname1;
    private final List<String> itemname2;

    public CustomAdapterOpenPorts(Activity context, List<String> itemname, List<String> itemname1, List<String> itemname2) {
        super(context, R.layout.mylist4xml, itemname);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
        this.itemname1=itemname1;
        this.itemname2=itemname2;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist4xml, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        TextView type = (TextView) rowView.findViewById(R.id.textView6);
        txtTitle.setText("Port: "+ itemname.get(position));
        extratxt.setText("Ser/app: " + itemname1.get(position));
        type.setText(itemname2.get(position).toUpperCase());
        return rowView;

    }
}
