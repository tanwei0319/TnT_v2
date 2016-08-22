package com.example.android.tnt_v2;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FeedbackActivity extends AppCompatActivity {
    private EditText feedbackTitle;
    private EditText feedbackDesc;
    boolean connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackTitle = (EditText) findViewById(R.id.feedbackTitle);
        feedbackDesc = (EditText) findViewById(R.id.feedbackDesc);

        if (getSupportActionBar() != null) { getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
    }

    public String getDeviceName(){
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        if (model.startsWith(manufacturer)){
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s){
        if (s == null || s.length() == 0){
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)){
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public void postData(){
        String fullUrl = "https://docs.google.com/forms/d/e/1FAIpQLSe065MgkQ1ePa-fkn5uT_EQuF7_L2r9f3TV_Ce8Y0cirHXhdA/formResponse";
        HttpRequest mReq = new HttpRequest();
        String mDevice = getDeviceName();
        String mTitle = feedbackTitle.getText().toString();
        String mDesc = feedbackDesc.getText().toString();

        String data = null;
        try {
            data = "entry.1100391272=" + URLEncoder.encode(mDevice, "UTF-8") + "&" + "entry.2041854041=" + URLEncoder.encode(mTitle, "UTF-8") + "&" + "entry.256253437=" + URLEncoder.encode(mDesc, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mReq.sendPost(fullUrl, data);
    }

    public boolean checkInternetConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        } else if (id == R.id.action_send){
            if (feedbackTitle.getText().toString().equals("") || feedbackDesc.getText().toString().equals("")){
                Toast.makeText(FeedbackActivity.this, "Feedback not sent. Both fields are required.", Toast.LENGTH_SHORT).show();
            } else {
                connectionStatus = checkInternetConnection();

                if (connectionStatus) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            postData();
                        }
                    });
                    thread.start();

                    Toast.makeText(FeedbackActivity.this, "Feedback sent. Thank you!", Toast.LENGTH_SHORT).show();
                    feedbackTitle.setText("");
                    feedbackDesc.setText("");
                } else {
                    Toast.makeText(FeedbackActivity.this, "Feedback not sent. You need Internet connection.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
