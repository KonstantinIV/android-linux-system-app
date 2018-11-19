package com.konsta.nufik.loputoo2.CustomLists;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.konsta.nufik.loputoo2.R;
import java.util.List;

/**
 * Created by Konstantin on 08-Jan-17.
 */

public class CustomAdapterPartitionTable extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> itemname;
    private final List<String> itemname1;
    private final List<String> itemname2;
    private final List<String> itemname3;
    private final List<String> itemname4;

    public CustomAdapterPartitionTable(Activity context, List<String> itemname, List<String> itemname1, List<String> itemname2,List<String> itemname3, List<String> itemname4) {
        super(context, R.layout.mylist3xml, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itemname1=itemname1;
        this.itemname2=itemname2;
        this.itemname3=itemname3;
        this.itemname4=itemname4;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist3xml, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        TextView txt2 = (TextView) rowView.findViewById(R.id.textView2);
        TextView txt3 = (TextView) rowView.findViewById(R.id.textView3);
        ImageView img = (ImageView) rowView.findViewById(R.id.imageView);

        img.setImageResource(R.drawable.harddrive);
        txtTitle.setTextColor(Color.parseColor("#00ff00"));
        extratxt.setTextColor(Color.parseColor("#4ae08c"));
        txt2.setTextColor(Color.parseColor("#4ae08c"));
        txt3.setTextColor(Color.parseColor("#4ae08c"));

        if (!itemname4.get(position).equals("1")){
            rowView.setBackgroundResource(R.drawable.partitions_darker_background);
            txtTitle.setTextColor(Color.parseColor("#009e00"));
            String color = "#32ad68";

            extratxt.setTextColor(Color.parseColor(color));
            txt2.setTextColor(Color.parseColor(color));
            txt3.setTextColor(Color.parseColor(color));
            img.setImageResource(R.drawable.partition);
            RelativeLayout.LayoutParams marginParams = new RelativeLayout.LayoutParams(img.getLayoutParams());
            marginParams.setMargins(60,40,0,26);
            img.setLayoutParams(marginParams);
        }

        txtTitle.setText(itemname.get(position));
        extratxt.setText("Space: " + itemname1.get( position));
        txt2.setText("Type: " + itemname2.get(position));
        txt3.setText("Mounted: " +itemname3.get(position));
        return rowView;

    }
}
