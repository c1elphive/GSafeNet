package com.example.smarthome;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class AlarmService extends AppCompatActivity {
    Button koumpi1 ;
    TextView alarm3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_service);
        alarm3=(TextView)findViewById(R.id.alarm3) ;
        koumpi1=(Button)findViewById(R.id.koumpi1);
        koumpi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startService(new Intent(getApplication(),alarmBackground.class));
            }
        });

            }

    }

