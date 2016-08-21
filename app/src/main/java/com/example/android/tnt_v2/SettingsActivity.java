package com.example.android.tnt_v2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.tnt_v2.adapter.CustomListAdapter;
import com.example.android.tnt_v2.datasource.ListDataSource;

public class SettingsActivity extends AppCompatActivity {
    ListView settings_listview;
    int [] settings_icons = {R.drawable.feedback_icon};
    String[] settings_head, settings_sub;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_listview = (ListView)findViewById(R.id.listView);
        settings_head = getResources().getStringArray(R.array.settings_head_arrays);
        settings_sub = getResources().getStringArray(R.array.settings_sub_arrays);

        final SharedPreferences sharedPreferences = SettingsActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);

        onCreateList();

        settings_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                if (position == 0){
                    Intent intent = new Intent(SettingsActivity.this, FeedbackActivity.class);
                    startActivity(intent);
                }
            }
        });

        if (getSupportActionBar() != null) { getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
    }

    public void onCreateList() {
        int i = 0;
        adapter = new CustomListAdapter(getApplicationContext(), R.layout.list_settings);
        settings_listview.setAdapter(adapter);
        for (String settingsHead : settings_head) {
            ListDataSource settingsDataSource = new ListDataSource(settings_icons[i], settingsHead, settings_sub[i]);
            adapter.add(settingsDataSource);
            i++;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
