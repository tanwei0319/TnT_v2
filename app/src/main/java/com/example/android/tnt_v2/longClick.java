package com.example.android.tnt_v2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Lakshmi on 9/7/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Lakshmi on 9/7/2016.
 */
public class longClick implements View.OnLongClickListener {
    Context context;
    String id;
    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("expense Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }
                        else if (item == 1) {

                            boolean deleteSuccessful = new crud(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "expense record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete expense record.", Toast.LENGTH_SHORT).show();
                            }


                            //((MainActivity) context).countRecords();
                            ((MainActivity) context).readRecords();

                        }
                        dialog.dismiss();

                    }
                }).show();
        return false;
    }

    public void editRecord(final int expenseId) {
        final crud table = new crud(context);
        expense exp = table.readSingleRecord(expenseId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.popup_new_expense, null, false);
        final EditText et_description = (EditText) formElementsView.findViewById(R.id.et_description);
        final EditText et_total = (EditText) formElementsView.findViewById(R.id.et_total);
        final Spinner sp_categories =(Spinner)formElementsView.findViewById(R.id.sp_categories);

        Calendar c = Calendar.getInstance();
        final String today = c.get(Calendar.DAY_OF_MONTH)+"/" + (c.get(Calendar.MONTH)+1) +"/"+c.get(Calendar.YEAR);

        sp_categories.setOnItemSelectedListener(new SpinnerActivity());

        et_description.setText(exp.d);
        et_total.setText(exp.t);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                expense exp = new expense();
                                exp.id = expenseId;
                                exp.d = et_description.getText().toString();
                                exp.t = et_total.getText().toString();
                                exp.c = String.valueOf(sp_categories.getSelectedItem());
                                exp.date = today;


                                boolean updateSuccessful = table.update(exp);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Expense record was updated.", Toast.LENGTH_SHORT).show();
                                }

                                else{
                                    Toast.makeText(context, "Unable to update expense record.", Toast.LENGTH_SHORT).show();
                                }


                                //((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();

                                dialog.cancel();
                            }

                        }).show();

    }
}


