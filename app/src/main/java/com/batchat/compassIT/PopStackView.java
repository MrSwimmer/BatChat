package com.batchat.compassIT;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.batchat.compassIT.Realm.RealSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import devlight.io.library.ArcProgressStackView;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.batchat.compassIT.MainActivity.mainProf;
import static com.batchat.compassIT.MainActivity.profarea;

/**
 * Created by Севастьян on 04.11.2017.
 */

class PopStackView {
    private static final int MODEL_COUNT = 5;
    private int[] mStartColors = new int[MODEL_COUNT];
    final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
    final ArrayList<ArcProgressStackView.Model> modelslang = new ArrayList<>();
    private final Realm mRealm = MainApplication.getInstance().mainRealm;
    ArrayList<RealSkill> topLang = new ArrayList<RealSkill>();
    ArrayList<RealSkill> topSkill = new ArrayList<RealSkill>();

    public PopStackView(View view) {
        final String[] bgColors = view.getResources().getStringArray(R.array.medical_express);
        TextView mainProfText = (TextView) view.findViewById(R.id.prof_main_prof);
        mainProfText.setText(mainProf);
        topLang.clear();
        topSkill.clear();
        final String[] startColors = view.getResources().getStringArray(R.array.default_preview);
        final String[] lang = view.getResources().getStringArray(R.array.languages);
        mRealm.beginTransaction();
        RealmResults<RealSkill> books = mRealm.where(RealSkill.class).equalTo("area", profarea).findAll();
        Log.i("code", books.size()+"");
        if(!books.isEmpty()) {
            for(int i = books.size() - 1; i >= 0; i--) {
                RealSkill skill = books.get(i);
                if(Arrays.asList(lang).contains(skill.getSkill())){
                    Log.i("code", skill.getSkill()+ " " + "lang");
                    topLang.add(skill);

                } else {
                    Log.i("code", skill.getSkill()+ " " + "skill");
                    topSkill.add(skill);
                }
            }
        }
        mRealm.commitTransaction();

        for (int i = 0; i < MODEL_COUNT; i++) {
            mStartColors[i] = Color.parseColor(startColors[i]);
        }
        /*topSkill.sort(new Comparator<RealSkill>() {
            @Override
            public int compare(RealSkill o1, RealSkill o2) {
                if (o1.getCount() == o2.getCount()) return 0;
                else if (o1.getCount()> o2.getCount()) return 1;
                else return -1;
            }
        });*/
        int allcount = 0;
        int allcountlang = 0;
        Collections.sort(topSkill, new MyComparator());
        Collections.sort(topLang, new MyComparator());
        TextView list = (TextView) view.findViewById(R.id.text_list);
        TextView listlang = (TextView) view.findViewById(R.id.text_list_lang);
        String names = "";
        String langs = "";
        for(int i=0; i<5; i++){
            int ip = i+1;
            names+="#"+ip+" "+topSkill.get(i).getSkill()+"\n";
            allcount += topSkill.get(i).getCount();
            Log.i("code", topSkill.get(i).getSkill());
        }
        for(int i=0; i<5&&i<topLang.size(); i++) {
            int ip = i + 1;
            langs += "#" + ip + " " + topLang.get(i).getSkill()+"\n";
            allcountlang += topLang.get(i).getCount();
        }
        list.setText(names);
        listlang.setText(langs);
        for(int i=0; i<5; i++){
            RealSkill top5 = topSkill.get(i);
            Log.i("code", "top"+i+" "+top5.getSkill()+" "+top5.getCount());
            models.add(new ArcProgressStackView.Model(top5.getSkill(), top5.getCount()*100/allcount, Color.parseColor(bgColors[i]), mStartColors[i]));
        }
        for(int i=0; i<5&&i<topLang.size(); i++){
            RealSkill top5lang = topLang.get(i);
            modelslang.add(new ArcProgressStackView.Model(top5lang.getSkill(), top5lang.getCount()*100/allcountlang, Color.parseColor(bgColors[i]), mStartColors[i]));
        }
        Log.i("code", models.size()+"");
        final ArcProgressStackView arcProgressStackView = (ArcProgressStackView) view.findViewById(R.id.apsv);
        final ArcProgressStackView arcProgressStackViewLang = (ArcProgressStackView) view.findViewById(R.id.apsvlang);
        if(profarea.equals("android")||profarea.equals("backend")||profarea.equals("game")||profarea.equals("data")||profarea.equals("ios")){

        }
        arcProgressStackView.setModels(models);
        arcProgressStackViewLang.setModels(modelslang);


        /*ListView listStack = (ListView) view.findViewById(R.id.stack_list);
        ListView listStackLang = (ListView) view.findViewById(R.id.stack_listlang);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.my_item, names);
        ArrayAdapter<String> adapterlang = new ArrayAdapter<String>(view.getContext(), R.layout.my_item, langs);

        // присваиваем адаптер списку
        listStack.setAdapter(adapter);
        listStackLang.setAdapter(adapterlang);*/
        Log.i("code", models.size()+"");
        Log.i("code", modelslang.size()+"");
    }
    class MyComparator implements Comparator<RealSkill> {
        @Override
        public int compare(RealSkill o1, RealSkill o2) {
            if (o1.getCount() == o2.getCount()) return 0;
            else if (o1.getCount()> o2.getCount()) return -1;
            else return 1;
        }
    }
}
