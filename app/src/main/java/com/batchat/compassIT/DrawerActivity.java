package com.batchat.compassIT;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.batchat.compassIT.Realm.VacancysSkills;
import com.batchat.compassIT.hh.APIService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private APIService service;
    List<Skill> masskills = new ArrayList<Skill>();
    public static ArrayList<String> stackskills = new ArrayList<String >();
    public static Realm mRealm;
    public static String allStack;
    private String PopularStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mRealm = Realm.getInstance(this);

        //получаем ключи. тут 0-имя для поиска, 1-название для файла и ключ для поиска, 2-ключ для поиска
        Bundle bundle = getIntent().getExtras();
        String extramas[] = bundle.getStringArray("keys");

        //парсим стек технологий по профессии из файла 1 в stackskills
        String text = extramas[1]+".txt";
        ParseStackFromFile(text);

        //запускаем асинк
        SearchIDandV searchID = new SearchIDandV();
        searchID.execute(extramas);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_camera) {
            fragmentClass = Profile.class;
            try{
                fragment = (Fragment) fragmentClass.newInstance();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void ParseStackFromFile(String text){
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
        allStack = total.toString();
        int pos=0;
        for(int i=0; i<allStack.length(); i++){
            if(allStack.charAt(i)==','){
                stackskills.add(allStack.substring(pos, i));
                pos=i+1;
            }
        }
    }
    public void ReadBase(String[] maskeys){
        mRealm.beginTransaction();
        RealmResults<VacancysSkills> stacks = mRealm.allObjects(VacancysSkills.class);
        if(!stacks.isEmpty()) {
            for(int i = stacks.size() - 1; i >= 0; i--) {

                String skills = stacks.get(i).getSkills();
                String area = stacks.get(i).getArea();
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
}
