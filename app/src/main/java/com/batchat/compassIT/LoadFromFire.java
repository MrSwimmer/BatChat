package com.batchat.compassIT;

import android.support.annotation.NonNull;
import android.util.Log;

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

/**
 * Created by Севастьян on 29.10.2017.
 */

public class LoadFromFire {
    private StorageReference mStorageRef;

    public LoadFromFire(String name) {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference stacks = mStorageRef.child("countstack/"+name+".txt");
        File localFile = null;
        try {
            localFile = File.createTempFile(name, "txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalLocalFile = localFile;
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
