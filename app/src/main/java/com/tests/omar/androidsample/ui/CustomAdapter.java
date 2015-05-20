package com.tests.omar.androidsample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tests.omar.androidsample.R;
import com.tests.omar.androidsample.entities.Info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by omar on 19/05/2015.
 */
public class CustomAdapter extends BaseAdapter{

    ArrayList<Info> data;
    Context context;
    LayoutInflater mInflater;

    public CustomAdapter(Context _context, ArrayList _data){
        this.data = _data;
        this.context = _context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CompleteListViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_layout,null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        }else{
            viewHolder = (CompleteListViewHolder) v.getTag();
        }

        String formatDate = dateFormatter(Long.valueOf(data.get(position).getTime()));

        viewHolder.tv1.setText(data.get(position).getMagnitude());
        viewHolder.tv2.setText(data.get(position).getPlace());
        viewHolder.tv3.setText(formatDate);
        return v;
    }

    public String dateFormatter(long millisec) {
        // DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        DateFormat formatter = new SimpleDateFormat("EEEE MMM dd HH:mm:ss zzz yyyy");

        long now = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisec);
        return formatter.format(calendar.getTime());

    }

    class CompleteListViewHolder{
        public TextView tv1,tv2,tv3;

        public CompleteListViewHolder(View base){
            tv1 = (TextView) base.findViewById(R.id.textView1);
            tv2 = (TextView) base.findViewById(R.id.textView2);
            tv3 = (TextView) base.findViewById(R.id.textView3);
        }

    }



}
