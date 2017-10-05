package com.batchat.batchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Севастьян on 22.09.2017.
 */

public class ChooseProfession extends Activity {
    LinearLayout Manager, Proger, UIUX, SysAdmin, Director, Writer, Unknow;
    String Choice = "nothing";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professions);

        Manager = (LinearLayout) findViewById(R.id.manager);
        Proger = (LinearLayout) findViewById(R.id.proger);
        UIUX = (LinearLayout) findViewById(R.id.uiux);
        SysAdmin = (LinearLayout) findViewById(R.id.sysadm);
        Director = (LinearLayout) findViewById(R.id.director);
        Writer = (LinearLayout) findViewById(R.id.writer);
        Unknow = (LinearLayout) findViewById(R.id.unknow);

        Manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choice = "manager";
            }
        });
        Proger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProf("proger");
            }
        });
    }
    private void goToProf(String prof){
        switch (prof){
            case "proger" :
                Intent intent = new Intent(ChooseProfession.this, ChooseProger.class);
                startActivity(intent);
        }
    }
}