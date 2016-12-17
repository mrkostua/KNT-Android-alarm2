package com.example.knt_lab1test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class AlarmService2 extends Service
{

    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("AlarmService","In receiver");

        boolean alarmCondition = intent.getExtras().getBoolean("alarmConditionKey",false);


            mediaPlayer = MediaPlayer.create(this,R.raw.ringtone1);
            mediaPlayer.start();
            Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
