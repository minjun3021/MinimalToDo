package com.example.kmj.minimaltodo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<Data> data;
    ImageButton delete;
    ImageButton edit;
    OnItemClickListener onItemClickListener;
    public ListViewAdapter(ArrayList<Data> data,OnItemClickListener onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        TextView maintitle = v.findViewById(R.id.maintitle);
        TextView subtitle = v.findViewById(R.id.subtitle);
        delete = v.findViewById(R.id.delete);
        edit = v.findViewById(R.id.edit);
        maintitle.setText(data.get(position).getMaintext());
        subtitle.setText(data.get(position).getSubtext());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onDeleteClickListener(position);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onEditClickListener(position);
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

        return v;
    }
}
