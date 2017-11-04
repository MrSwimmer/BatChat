package com.batchat.compassIT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Севастьян on 05.10.2017.
 */

public class ChooseProger extends Activity {
    LinearLayout Frontend, Backend, Android, SQL, QA, DataS, GameDev, IOS;
    String namesFiles[] = new String[]{"android", "frontend", "backend", "data", "game", "ios", "qa", "sql"};
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progers);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Frontend = (LinearLayout) findViewById(R.id.front);
        Backend = (LinearLayout) findViewById(R.id.back);
        Android = (LinearLayout) findViewById(R.id.mobile);
        SQL = (LinearLayout) findViewById(R.id.sql);
        QA = (LinearLayout) findViewById(R.id.qa);
        DataS = (LinearLayout) findViewById(R.id.datas);
        GameDev = (LinearLayout) findViewById(R.id.gamedev);
        IOS = (LinearLayout) findViewById(R.id.decktop);
        Frontend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("Frontend разработчик", namesFiles[1]);
            }
        });
        Backend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("Backend разработчик", namesFiles[2]);
            }
        });
        Android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("Android разработчик", namesFiles[0]);
            }
        });
        SQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("SQL разработчик", namesFiles[7]);
            }
        });
        QA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("QA инженер", namesFiles[6]);
            }
        });
        DataS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("Data Scientist", namesFiles[3]);
            }
        });
        GameDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("Разработчик игр", namesFiles[4]);
            }
        });
        IOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain("IOS разработчик", namesFiles[5]);
            }
        });

    }
    void startMain(String prof, String area){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("mainprof", prof);
        editor.putString("areaprof", area);
        editor.apply();
        Intent intent = new Intent(ChooseProger.this, MainActivity.class);
        startActivity(intent);
    }
}
