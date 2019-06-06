package com.pretty.strawberry.workermanagerex;

/**
 * Notification App
 *
 *
 * We will build the simple Notification App which contains two buttons.
 * On Click of First Button, Notification will be displayed only once
 * and On Click of Second Button, Notification will be displayed once every 1 minute.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.pretty.strawberry.workermanagerex.Worker.NotiWorker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    WorkManager workManager;
    AppCompatButton btnStartOneTimeRequest , btnStartPeriodicRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workManager = WorkManager.getInstance();
        btnStartOneTimeRequest = findViewById(R.id.btn_one_time);
        btnStartPeriodicRequest = findViewById(R.id.btn_periodic_time);


        btnStartOneTimeRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // what conditions need to be satisfied before the workRewuest can run
                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(false)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                /*n this step, build the OneTimeWorkRequest using OneTimeWorkRequest.Builder() function
                and pass the NotiWorker class as it's parameter.
                After that, set the Constraints to it so that when all those constraints are fulfilled,
                then only it can run, and at last build it.
                Finally, enqueue this WorkRequest using enqueue() method of WorkManager
                class for background processing.

                 */
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(NotiWorker.class)
                        .setConstraints(constraints)
                        .build();

                workManager.enqueue(oneTimeWorkRequest);
            }
        });


        btnStartPeriodicRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(false)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(NotiWorker.class , 1 , TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();

                workManager.enqueue(periodicWorkRequest);
            }
        });

    }
}
