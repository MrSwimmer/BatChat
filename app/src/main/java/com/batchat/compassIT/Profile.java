package com.batchat.compassIT;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;
import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by Севастьян on 05.07.2017.
 */

public class Profile extends Fragment {
    static WaveLoadingView mWaveLoadingView;
    static ListView TopfiveL;
    static ArrayAdapter<String> adapter;
    public static Context context;
    static LinearLayout allinfo;
    static final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
    static ArcProgressStackView arcProgressStackView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile, container, false);
        context=getContext();
        arcProgressStackView = (ArcProgressStackView) v.findViewById(R.id.apsv);
        allinfo = (LinearLayout) v.findViewById(R.id.allinf);
        TopfiveL = (ListView) v.findViewById(R.id.topfive);
        mWaveLoadingView = (WaveLoadingView) v.findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setTopTitle("Идет загрузка данных");
        mWaveLoadingView.setCenterTitleColor(Color.WHITE);
        mWaveLoadingView.setProgressValue(0);
        mWaveLoadingView.setBorderWidth(10);
        mWaveLoadingView.setAmplitudeRatio(60);
        mWaveLoadingView.setWaveColor(R.color.second_slide_background);
        mWaveLoadingView.setBorderColor(R.color.second_slide_background);
        mWaveLoadingView.setAnimDuration(2500);
        mWaveLoadingView.pauseAnimation();
        mWaveLoadingView.resumeAnimation();
        mWaveLoadingView.cancelAnimation();
        mWaveLoadingView.startAnimation();
        return v;
    }

    public static void setProgress(float pr){
        mWaveLoadingView.pauseAnimation();
        mWaveLoadingView.setProgressValue((int) pr);
        mWaveLoadingView.setCenterTitle(((int) pr)+"%");
        mWaveLoadingView.resumeAnimation();
        //progress.setText(string);
    }
}
