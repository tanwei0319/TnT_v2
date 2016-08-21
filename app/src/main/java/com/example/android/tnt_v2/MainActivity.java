package com.example.android.tnt_v2;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                title  = "Home";

                break;
            case R.id.nav_expenditure:
                fragment = new ExpenditureFragment();
                title = "Expenditure";
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    public void buttonClicked(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.popup_new_expense,null, false);
        final EditText et_description = (EditText) formElementsView.findViewById(R.id.et_description);
        final EditText et_total = (EditText) formElementsView.findViewById(R.id.et_total);
        final Spinner sp_categories =(Spinner) formElementsView.findViewById(R.id.sp_categories);

        Calendar c = Calendar.getInstance();
        final String today = c.get(Calendar.DAY_OF_MONTH)+"/" + (c.get(Calendar.MONTH)+1) +"/"+c.get(Calendar.YEAR);

        sp_categories.setOnItemSelectedListener(new SpinnerActivity());



        new android.app.AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Expenses")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String desc = et_description.getText().toString();
                                String tot = et_total.getText().toString();
                                String categ = String.valueOf(sp_categories.getSelectedItem());

                                //Toast.makeText(context, categ, Toast.LENGTH_SHORT).show();

                                expense exp = new expense();
                                exp.d = desc;
                                exp.t = tot;
                                exp.c = categ;
                                exp.date = today;

                                Toast.makeText(context, exp.c, Toast.LENGTH_SHORT).show();


                                boolean createSuccessful = new crud(context).create(exp);

                                if (createSuccessful) {
                                    Toast.makeText(context, "Expense information was saved.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to save expense information.", Toast.LENGTH_SHORT).show();
                                }


                                //((MainActivity) context).countRecords();
                                //((MainActivity) context).readRecords();

                                dialog.cancel();
                            }

                        }).show();

    }

    public void show(View view) {
        readRecords();
    }


    public void countRecords() {
        setContentView(R.layout.popup_new_expense);
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        int recordCount = new crud(this).count();
        String r = recordCount + " records found!";

        textViewRecordCount.setText(r);
    }

    public void readRecords() {
        setContentView(R.layout.list);
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<expense> expenses = new crud(this).read();

        if (expenses.size() > 0) {

            for (expense obj : expenses) {

                int id = obj.id;
                String desc = obj.d;
                String tot = obj.t;
                String categ = obj.c;
                String today = obj.date;

                String textViewContents = today+  "===" + categ + "==" + desc + " = " + tot;

                TextView textViewExpenseItem= new TextView(this);
                textViewExpenseItem.setPadding(0, 10, 0, 10);
                textViewExpenseItem.setText(textViewContents);
                textViewExpenseItem.setTag(Integer.toString(id));

                textViewExpenseItem.setOnLongClickListener(new longClick());

                linearLayoutRecords.addView(textViewExpenseItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
