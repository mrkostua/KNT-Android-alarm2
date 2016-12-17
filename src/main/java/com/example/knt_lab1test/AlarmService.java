package com.example.knt_lab1test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class AlarmService extends Service
{
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean alarmCondition = intent.getExtras().getBoolean("alarmConditionKey");

        if(alarmCondition)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.ringtone1);
            mediaPlayer.start();

            Intent intent_NextActivity = new Intent(AlarmService.this,NextActivity.class);
            intent_NextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent_NextActivity);
        }
        else if(!alarmCondition)
        {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
