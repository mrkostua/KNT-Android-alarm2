package com.example.knt_lab1test;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity
{
    private TimePicker timePicker;
    public int hours , minutes =0;
    private Calendar calendar;
    private boolean alarmCondition = false;
    private PendingIntent pendingIntent;
    private Intent intent_AlarmReceiver;
    private AlarmManager alarmManager;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activty);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
         intent_AlarmReceiver = new Intent(MainActivity.this,AlarmReceiver.class);
    }



    public void bWitaj_onClickMethod(View view)
    {
        alarmCondition = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hours = timePicker.getHour();
            minutes = timePicker.getMinute();
        }
        else
        {
            hours = timePicker.getCurrentHour();
            minutes = timePicker.getCurrentMinute();
        }

        //otrzymujemy calendar wykorzystujac domyslna i lokalna strefe czasowa.
        //zwraca calendar oparty na  aktualny czas
        calendar = Calendar.getInstance();
        //ustawiamy calendar czas od podanej wartosci long
        calendar.setTimeInMillis(System.currentTimeMillis());

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        // konwertujemy z int na String
        String sHour =  String.valueOf(hours);
        String sMinute =  String.valueOf(minutes);

        //zmieniamy wyglad minuty z 5 na 05
        if(minutes<10)
            sMinute="0" + String.valueOf( minutes);


        //musimy stworzyc Intent ktory bedzie urochomial masz Recivier ktory w swojej kolej uruchomi Service
        //To jest potrzebne dla tego zeby caly proces dzialal nie na glownym watku i wykonywal dzialania w tle czyli w Service


            intent_AlarmReceiver.putExtra("alarmConditionKey",alarmCondition);

        //Jest w oczykiwaniu na uruchmianie naszego zamiaru czyli intent_AlarmReceiver
        //odklada uruchomienia intentu przed okreslonym czasem
        /*
        *context
        *0 - prywatny kod rzadania dla nadawcy
        * intent_AlarmReceiver - intent ktory bedzie (broadcast) transmisjowany
        * FLAG - dla kontrolu nieokreslonych czesciej intentu (czyli jakie danne moga byc dostarczane) gdy intent jest wyslany
        * FLAG_UPDATE_CURRENT - jezeli opisany pendingIntent istnieje to zapisac go tylko zamienic ekstra danne z dannymi ktore
        * znajduja sie w Intent
         */
        pendingIntent = pendingIntent.getBroadcast(MainActivity.this,0,intent_AlarmReceiver,PendingIntent.FLAG_UPDATE_CURRENT);

        calendar.set(Calendar.HOUR_OF_DAY,hours);
        calendar.set(Calendar.MINUTE,minutes);

        //if (hour>currentHour); if(hour == currentHour)(minute > currentMinute)

        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

    }

}
