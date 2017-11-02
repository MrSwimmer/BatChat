package com.batchat.compassIT;

import android.os.AsyncTask;

import com.batchat.compassIT.Realm.RealSkill;

/**
 * Created by Севастьян on 03.11.2017.
 */

public class LoadToReal extends AsyncTask<String, Void, Void>{

    @Override
    protected Void doInBackground(String... params) {
        MainApplication.getInstance().mainRealm.beginTransaction();
        RealSkill realSkill = MainApplication.getInstance().mainRealm.createObject(RealSkill.class);
        realSkill.setArea(params[2]);
        realSkill.setCount(Integer.parseInt(params[1]));
        realSkill.setSkill(params[0]);
        MainApplication.getInstance().mainRealm.commitTransaction();
        return null;
    }



}
