package com.batchat.compassIT;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class MainActivity extends Activity {
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    static String mainProf, profarea;
    Context context;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_horizontal_ntb);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mainProf = mSettings.getString("mainprof", "Профессия");
        profarea = mSettings.getString("areaprof", "no");
        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                View view =  LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_profile, null, false);
                switch (position) {
                    case 0:
                        view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_news, null, false);
                        break;
                    case 1:
                        view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_stack, null, false);
                        new PopStackView(view);
                        break;
                    case 2:
                        view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_profile, null, false);
                        new ProfileView(view);
                        break;
                    case 3:
                        view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_stat, null, false);
                        break;
                    case 4:
                        view = LayoutInflater.from(
                                getBaseContext()).inflate(R.layout.item_settings, null, false);
                        break;
                }
                container.addView(view);
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_news),
                        Color.parseColor(colors[0]))
                        .title("Новости")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_stack),
                        Color.parseColor(colors[1]))
                        .title("Стэк")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_prof),
                        Color.parseColor(colors[2]))
                        .title("Профиль")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_stat),
                        Color.parseColor(colors[3]))
                        .title("Статистика")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_settings),
                        Color.parseColor(colors[0]))
                        .title("Настройки")
                        .build()
        );
        navigationTabBar.setModels(models);
        if(!mSettings.getBoolean("profileexist", false)){
            navigationTabBar.setViewPager(viewPager, 1);
        } else {
            navigationTabBar.setViewPager(viewPager, 2);
        }
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
