package com.batchat.compassIT;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Севастьян on 04.11.2017.
 */

public class ProfileView {
    ListView listStack;
    TextView mainProf;
    public ProfileView(View view) {
        listStack = (ListView) view.findViewById(R.id.prof_list_stack);
        mainProf = (TextView) view.findViewById(R.id.prof_main_prof);
        mainProf.setText(MainActivity.mainProf);
        String[] skills ={"Django", "Python"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, skills);
        listStack.setAdapter(adapter);
    }
}
