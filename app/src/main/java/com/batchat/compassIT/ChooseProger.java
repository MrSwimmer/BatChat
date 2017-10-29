package com.batchat.compassIT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Севастьян on 05.10.2017.
 */

public class ChooseProger extends Activity{
    String Choice="nothing";
    String maskeys[];
    LinearLayout Frontend, Backend, Mobile, SQL, QA, DataS, GameDev, Desktop;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progers);

        Frontend = (LinearLayout) findViewById(R.id.front);
        Backend = (LinearLayout) findViewById(R.id.back);
        Mobile = (LinearLayout) findViewById(R.id.mobile);
        SQL = (LinearLayout) findViewById(R.id.sql);
        QA = (LinearLayout) findViewById(R.id.qa);
        DataS = (LinearLayout) findViewById(R.id.datas);
        GameDev = (LinearLayout) findViewById(R.id.gamedev);
        Desktop = (LinearLayout) findViewById(R.id.decktop);

        Frontend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProger.this, DrawerActivity.class);
                maskeys = new String[]{"Frontend разработчик", "frontend", "developer"};
                intent.putExtra("keys",maskeys);
                startActivity(intent);
            }
        });
    }
}
