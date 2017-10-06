package com.batchat.compassIT;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.batchat.compassIT.SearchID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Севастьян on 06.10.2017.
 */

public class LoaderBase extends Activity {
    RealmConfiguration realmConfFront;
    public static Realm realmFront;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String extramas[] = bundle.getStringArray("keys");
        if(realmConfFront==null){
            realmConfFront = new RealmConfiguration.Builder(this).name(extramas[0]).build();
            SearchID searchID = new SearchID();
            searchID.execute(extramas);
        }
    }
}
