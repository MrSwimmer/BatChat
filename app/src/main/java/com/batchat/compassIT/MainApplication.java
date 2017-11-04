package com.batchat.compassIT;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.realm.Realm;


/**
 * Created by Севастьян on 08.10.2017.
 */

public class MainApplication extends Application {
    private SharedPreferences mSettings;
    public Realm mainRealm;
    public static final String APP_PREFERENCES = "mysettings";
    private static MainApplication singleton;
    public ArrayList<String> FrontStackSkills=new ArrayList<String>(),
            BackendStackSkills=new ArrayList<String>(),
            LanguagesStack = new ArrayList<String>();
    public ArrayList<Skill> CountSkillsFront=new ArrayList<Skill>(), CountBackendSkills;
    // Возвращает экземпляр данного класса
    public static MainApplication getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;
        mainRealm = Realm.getInstance(this);
    }
}
