package com.batchat.batchat;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.batchat.batchat.Realm.VacancysSkills;
import com.batchat.batchat.hh.APIService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class SplashActivity extends Activity {

    private APIService service;
    List<Skill> masskills = new ArrayList<Skill>();
    public static ArrayList<String> stackskills = new ArrayList<String >();
    public static Realm mRealm;
    private Button getTop;
    public static TextView txt_percentage;
    public static String allStack;
    private String PopularStack;
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.splash);

        getTop = (Button) findViewById(R.id.getTop);
        txt_percentage= (TextView) findViewById(R.id.txt_percentage);

        ParseStackFromFile();

        Skill skill = new Skill();
        skill.setSkill("android");
        masskills.add(skill);

        //Парсим сначала id, затем сами вакансии и добавляем их keyskills в бд
        //с тегом вакансии. Пример: android
        SearchID searchID = new SearchID();
        searchID.execute("android");

        mRealm = Realm.getInstance(this);

        getTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                masskills.clear();
                //гоним по базе
                mRealm.beginTransaction();
                RealmResults<VacancysSkills> books = mRealm.allObjects(VacancysSkills.class);
                if(!books.isEmpty()) {

                    for(int i = books.size() - 1; i >= 0; i--) {

                        String skills = books.get(i).getSkills();
                        String area = books.get(i).getArea();
                        int pos=0;
                        //Строка стека в виде Скилл,Скилл,
                        for(int k=0; k<skills.length();k++){
                            if(skills.charAt(k)==','){
                                String sub = skills.substring(pos,k).toLowerCase();
                                boolean meet = false;
                                for(int p=0; p<masskills.size(); p++){
                                    //Log.i("code", masskills.get(p).getSkill()+" "+ sub);
                                    //Если нашли, то плюсуем
                                    if(masskills.get(p).getSkill().equals(sub)){
                                        masskills.get(p).addCount();
                                        meet = true;
                                        break;
                                    }
                                    else {

                                    }
                                }
                                if(!meet){
                                    Skill skill = new Skill();
                                    skill.setSkill(sub);
                                    skill.addCount();
                                    //если такого еще не было, то добавляем в Arraylist
                                    masskills.add(skill);
                                }
                                pos=k+1;
                            }
                        }
                    }

                    //сортируем полученный ArrayList
                    Collections.sort(masskills, Skill.COMPARE_BY_COUNT);
                    for(int p=0; p<masskills.size(); p++){
                        Log.i("code", "s: "+masskills.get(p).getSkill()+ " c: "+masskills.get(p).getCount());
                        PopularStack+=masskills.get(p).getSkill()+",";
                    }
                    Log.i("code", PopularStack);
                }
                mRealm.commitTransaction();
            }
        });

    }
    void ParseStackFromFile(){
        AssetManager am = this.getAssets();
        InputStream is = null;
        try {
            is = am.open("parser.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line = "+";
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        allStack = total.toString();
        int pos=0;
        for(int i=0; i<allStack.length(); i++){
            if(allStack.charAt(i)==','){
                stackskills.add(allStack.substring(pos, i));
                pos=i+1;
            }
        }
    }
}