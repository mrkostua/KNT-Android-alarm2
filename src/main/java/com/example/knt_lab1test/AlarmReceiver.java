package com.example.knt_lab1test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Администратор on 13.12.2016.
 */

public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {

        boolean alarmCondition = intent.getExtras().getBoolean("alarmConditionKey");
        Intent intent_alarmService = new Intent (context,AlarmService.class);
            intent_alarmService.putExtra("alarmConditionKey",alarmCondition);
            context.startService(intent_alarmService);

    }
}
