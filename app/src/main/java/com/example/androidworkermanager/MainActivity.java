package com.example.androidworkermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scheduleJob = findViewById(R.id.trigger_job);
        scheduleJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiresBatteryNotLow(true)
                        .build();

                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();

                WorkManager workManager = WorkManager.getInstance(getApplicationContext());
                workManager.enqueueUniquePeriodicWork("Counter", ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);
                Toast.makeText(MainActivity.this, "🎉 Scheduled job!", Toast.LENGTH_LONG).show();
            }
        });
    }
}