package com.batchat.compassIT.Intro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.View;


import com.batchat.compassIT.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class FirstStartActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

            getBackButtonTranslationWrapper()
                    .setEnterTranslation(new IViewTranslation() {
                        @Override
                        public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                            view.setAlpha(percentage);
                        }
                    });
            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.first_slide_background)
                    .buttonsColor(R.color.first_slide_buttons)
                    .title("IT Компас")
                    .description("Найди себя в IT!")
                    .build());
            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.second_slide_background)
                    .buttonsColor(R.color.second_slide_buttons)
                    .title("Кто ты?")
                    .description("Новичок Junior?\nПродвинутый Middle или Гуру-Senior?\nНеважно, здесь каждый найдет ответы!")
                    .build());

            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.third_slide_background)
                    .buttonsColor(R.color.third_slide_buttons)
//                        .possiblePermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS})
//                        .neededPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
                    .title("Musthave stack")
                    .description("Подборка самых востребованных технологий на рынке!")
                    .build());
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }
}