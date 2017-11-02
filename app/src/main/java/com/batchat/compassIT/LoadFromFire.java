package com.batchat.compassIT;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.batchat.compassIT.Realm.RealSkill;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.batchat.compassIT.Profile.context;

/**
 * Created by Севастьян on 29.10.2017.
 */

public class LoadFromFire {
    private StorageReference mStorageRef;
    String namesFiles[] = new String[]{"android", "frontend", "backend", "data", "game", "ios", "qa", "sql"};

    LoadFromFire(){
        mStorageRef = FirebaseStorage.getInstance().getReference();
        for(int i=0; i<8; i++){
            final String name = namesFiles[i];
            StorageReference stacks = mStorageRef.child("countstack/"+name+".txt");
            File localFile = null;
            try {
                localFile = File.createTempFile("stacks"+i, "txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            final File finalLocalFile = localFile;
            final int finalI = i;
            stacks.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            String s = "";
                            Scanner in = null;
                            try {
                                in = new Scanner(finalLocalFile);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            while(in.hasNext())
                                s += in.nextLine();
                            in.close();
                            Log.i("code", "textfromfire: "+s);
                            int begskill=0, begcount = 0;
                            for(int j=0; j<s.length(); j++){
                                if(s.charAt(j)==':'){
                                    begcount=j+1;
                                }
                                if(s.charAt(j)==','){
                                    String skill = s.substring(begskill, begcount-1);
                                    int count = Integer.parseInt(s.substring(begcount, j));
                                    MainApplication.getInstance().mainRealm.beginTransaction();
                                    RealSkill realSkill = MainApplication.getInstance().mainRealm.createObject(RealSkill.class);
                                    realSkill.setArea(namesFiles[finalI]);
                                    realSkill.setCount(count);
                                    realSkill.setSkill(skill);
                                    MainApplication.getInstance().mainRealm.commitTransaction();
                                    //new LoadToReal().execute(skill, String.valueOf(count), namesFiles[finalI]);
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    Log.i("code", "error");
                    // ...
                }
            });
        }
    }
}
