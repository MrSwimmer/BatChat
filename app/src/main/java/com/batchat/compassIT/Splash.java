package com.batchat.compassIT;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;

import com.batchat.compassIT.Intro.FirstStartActivity;


public class Splash extends Activity {

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити, а Splash Screen закрываем
//                Intent intent = new Intent(Splash.this, FirstStartActivity.class);
//                startActivity(intent);
                TaskStackBuilder.create(getApplicationContext())
                        .addNextIntentWithParentStack(new Intent(getApplicationContext(), ChooseProfession.class))
                        .addNextIntent(new Intent(getApplicationContext(), FirstStartActivity.class))
                        .startActivities();
            }
        }, 2000);
    }
}