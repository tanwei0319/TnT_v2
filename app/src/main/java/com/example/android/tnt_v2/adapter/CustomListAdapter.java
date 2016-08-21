package com.example.android.tnt_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tnt_v2.R;
import com.example.android.tnt_v2.datasource.ListDataSource;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter{
    List settingsList = new ArrayList();

    public CustomListAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler{
        ImageView iconSettings;
        TextView headSettings;
        TextView subSettings;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        settingsList.add(object);
    }

    @Override
    public int getCount() {
        return this.settingsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.settingsList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHandler handler;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_settings, parent, false);
            handler = new DataHandler();
            handler.iconSettings = (ImageView) row.findViewById(R.id.settingsItemIcon);
            handler.headSettings = (TextView) row.findViewById(R.id.settingsItemMain);
            handler.subSettings = (TextView) row.findViewById(R.id.settingsItemSub);
            row.setTag(handler);
        } else {
            handler = (DataHandler)row.getTag();
        }

        ListDataSource dataSource = (ListDataSource) this.getItem(position);
        handler.iconSettings.setImageResource(dataSource.getSettings_icon());
        handler.headSettings.setText(dataSource.getSettings_head());
        handler.subSettings.setText(dataSource.getSettings_sub());

        return row;
    }
}
