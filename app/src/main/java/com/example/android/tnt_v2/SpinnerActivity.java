package com.example.android.tnt_v2;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;


import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Lakshmi on 21/7/2016.
 */

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String item = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

