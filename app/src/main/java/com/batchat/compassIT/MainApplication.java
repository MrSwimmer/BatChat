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
        GetStacksFromFile(FrontStackSkills,"frontend.txt");
        GetStacksFromFile(BackendStackSkills, "backend.txt");
        GetStacksFromFile(LanguagesStack, "languages.txt");
        //mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mainRealm = Realm.getInstance(this);
    }
    void GetStacksFromFile(ArrayList<String> StackSkills, String text){
        AssetManager am = this.getAssets();
        InputStream is = null;
        try {
            is = am.open(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String allStack = total.toString();
        //Log.i("code", allStack);
        int pos=0;
        for(int i=0; i<allStack.length(); i++){
            if(allStack.charAt(i)==','){
                StackSkills.add(allStack.substring(pos, i).toLowerCase());
                pos=i+1;
            }
        }
    }
}
