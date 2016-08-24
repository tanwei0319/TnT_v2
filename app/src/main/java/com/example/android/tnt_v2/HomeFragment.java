package com.example.android.tnt_v2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.tnt_v2.R;

import org.w3c.dom.Text;

/**
 * Created by Wei on 25/07/16.
 */

public class HomeFragment extends Fragment {

    private TextView welcomeTitle;
    private TextView tntTitle;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        welcomeTitle = (TextView) rootView.findViewById(R.id.txtLabel);
        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/waltographUI.ttf");
        welcomeTitle.setTypeface(typeface1);

        tntTitle = (TextView) rootView.findViewById(R.id.txtLabel2);
        Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AveriaSansLibre-Bold.ttf");
        tntTitle.setTypeface(typeface2);

        Button button = (Button) rootView.findViewById(R.id.inspiration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SampleCarouselViewActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
