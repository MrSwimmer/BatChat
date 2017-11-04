package com.batchat.compassIT;

import android.view.View;

import com.batchat.compassIT.Realm.RealSkill;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.batchat.compassIT.MainActivity.profarea;

/**
 * Created by Севастьян on 04.11.2017.
 */

class PopStackView {
    final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
    private final Realm mRealm = MainApplication.getInstance().mainRealm;

    public PopStackView(View view) {
        final String[] startColors = view.getResources().getStringArray(R.array.default_preview);
        final String[] lang = view.getResources().getStringArray(R.array.languages);

        mRealm.beginTransaction();
        RealmResults<RealSkill> books = mRealm.where(RealSkill.class).equalTo("area", profarea).findAll();
        if(!books.isEmpty()) {
            for(int i = books.size() - 1; i >= 0; i--) {
                books.get(i).removeFromRealm();
            }
        }
        mRealm.commitTransaction();
        float ex = (float) 3.5;
        for(int i=0; i<5; i++){
            models.add(new ArcProgressStackView.Model("lol", i*10+5, Integer.parseInt(startColors[i])));
        }
        /*models.add(new ArcProgressStackView.Model("Circle", 25, bgColors[0], startColors[0]));
        models.add(new ArcProgressStackView.Model("Progress", 50, bgColors[1], mStartColors[1]));
        models.add(new ArcProgressStackView.Model("Stack", 75, bgColors[2], mStartColors[2]));
        models.add(new ArcProgressStackView.Model("View", 100, bgColors[3], mStartColors[3]));*/

        final ArcProgressStackView arcProgressStackView = (ArcProgressStackView) view.findViewById(R.id.apsv);
        arcProgressStackView.setModels(models);
    }
}
