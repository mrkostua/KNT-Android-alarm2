package com.example.knt_lab1test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_activty);


    }
    public void AlarmOffMethod(View view)
    {
        Intent intent_AlarmReceiverStop = new Intent(NextActivity.this, AlarmReceiver.class);
        // sending the condition of alarm if true - alarm on , if false - alarm off
        intent_AlarmReceiverStop.putExtra("alarmConditionKey", false);
        sendBroadcast(intent_AlarmReceiverStop);

        Intent intent_startMainActivity= new Intent(NextActivity.this,MainActivity.class);
        startActivity(intent_startMainActivity);

    }

}
