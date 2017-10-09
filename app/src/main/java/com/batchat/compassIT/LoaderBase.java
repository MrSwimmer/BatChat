package com.batchat.compassIT;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.batchat.compassIT.hh.APIService;
import com.batchat.compassIT.hh.GETPage.PageV;
import com.batchat.compassIT.hh.GETVacancy.Vacancy;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Севастьян on 06.10.2017.
 */

public class LoaderBase {
    private final APIService service;
    List<Skill> masskills;
    float percent, progress=0;

    float count=0;
    public LoaderBase(final String[] params) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hh.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);
        Call<PageV> callcount = service.getListURL(params[0], 1);
        callcount.enqueue(new Callback<PageV>() {
            @Override
            public void onResponse(Call<PageV> call, Response<PageV> response) {
                PageV page = response.body();
                count=(page.getFound()/20)-1;
                count=Math.min(count,99);
                percent = 100/(count*20);

                GetIDthenVacancy(params);
            }

            @Override
            public void onFailure(Call<PageV> call, Throwable t) {

            }
        });

    }
    void GetIDthenVacancy(final String[] params){
        MainApplication.getInstance().CountSkillsFront.clear();
        for(int i=1; i<count; i++){

            Call<PageV> call = service.getListURL(params[0], i);
            final int finalI1 = i;
            final int finalI = i;
            call.enqueue(new Callback<PageV>() {
                @Override
                public void onResponse(Call<PageV> call, Response<PageV> response) {
                    PageV page = response.body();
                    for(int j=0; j<20; j++) {
                        try {
                            String buf = page.getItems().get(j).getId();
                        }
                        catch (Exception e){
                            Log.i("code", finalI +" "+j);
                            return;
                        }
                        String buf = page.getItems().get(j).getId();
                        String toLow = page.getItems().get(j).getName().toLowerCase();

                        if(toLow.contains(params[1])||toLow.contains(params[2])){
                            Call<Vacancy> callv = service.getVacancy(buf);
                            final int finalJ = j;
                            callv.enqueue(new Callback<Vacancy>() {
                                @Override
                                public void onResponse(Call<Vacancy> call, Response<Vacancy> response) {
                                    if( finalJ ==19&& finalI1 ==count-1){
                                        Log.i("code", "OK");
                                    }
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post( new Runnable() {
                                        @Override
                                        public void run() {
                                            progress+=percent;
                                            String buf = String.valueOf(progress);
                                            Profile.setProgress(progress);
                                        }
                                    } );
                                    String description = response.body().getDescription();
                                    ArrayList<String> arrayList = MainApplication.getInstance().FrontStackSkills;
                                    for(int k=0; k<arrayList.size(); k++){
                                        if(description.toLowerCase().contains(arrayList.get(k))){
                                            Skill newskill = new Skill(arrayList.get(k));
                                            boolean meet=false;
                                            for(int o=0; o<MainApplication.getInstance().CountSkillsFront.size(); o++){
                                                //Log.i("code",arrayList.get(k)+" "+MainApplication.getInstance().CountSkillsFront.get(o).getSkill());
                                                if(MainApplication.getInstance().CountSkillsFront.get(o).getSkill().equals(arrayList.get(k))){
                                                    MainApplication.getInstance().CountSkillsFront.get(o).addCount();
                                                    meet=true;
                                                    //Log.i("code",arrayList.get(k));
                                                    break;
                                                }
                                            }
                                            if(!meet){
                                                MainApplication.getInstance().CountSkillsFront.add(newskill);
                                                //Log.i("code",newskill.getSkill());
                                            }
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<Vacancy> call, Throwable t) {
                                    Log.i("code", "SVBID "+ t.toString());
                                }
                            });
                        }
                        /*if(finalI ==59){
                            for(int l=0; l<MainApplication.getInstance().CountSkillsFront.size(); l++){
                                Skill logskill= MainApplication.getInstance().CountSkillsFront.get(l);
                                Log.i("code", logskill.getSkill() +" "+logskill.getCount());
                            }
                        }*/
                    }
                }
                @Override
                public void onFailure(Call<PageV> call, Throwable t) {
                    Log.i("code", "SVBID"+ t.toString());
                }
            });

        }
    }
}
