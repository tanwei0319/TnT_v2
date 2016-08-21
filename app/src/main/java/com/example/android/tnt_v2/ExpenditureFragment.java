package com.example.android.tnt_v2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wei on 25/07/16.
 */

public class ExpenditureFragment extends Fragment {

    public ExpenditureFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_expenditure, container, false);

        return rootView;
    }
}

