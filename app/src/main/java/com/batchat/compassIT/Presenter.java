package com.batchat.compassIT;

import android.os.Environment;
import android.util.Log;

import com.batchat.compassIT.Realm.RealSkill;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.batchat.compassIT.Profile.context;

/**
 * Created by Севастьян on 10.10.2017.
 */

public class Presenter {

    private static final String DIR_SD = "CompassIT";
    String full = "";
    ArrayList<Skill> topfiveL = new ArrayList<Skill>();

    String[] topLang = new String[5];

    public Presenter(ArrayList<Skill> countskills, String area) {

        for(int i=0; i<countskills.size(); i++){
            MainApplication.getInstance().mainRealm.beginTransaction();
            RealSkill realSkill = MainApplication.getInstance().mainRealm.createObject(RealSkill.class);
            realSkill.setArea(countskills.get(i).getArea());
            realSkill.setCount(countskills.get(i).getCount());
            realSkill.setSkill(countskills.get(i).getSkill());
            MainApplication.getInstance().mainRealm.commitTransaction();
            full+=realSkill.getSkill()+" "+realSkill.getCount()+".";
        }
        Log.i("code", countskills.size()+"");
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath,area+".txt");
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            // пишем данные
            bw.write(full);
            // закрываем поток
            bw.close();
            Log.d("code", "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
