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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class AlarmService extends AppCompatActivity {
    Button koumpi1 ;
    Switch srvSwitch;
    TextView alarm3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_service);

        srvSwitch=(Switch)findViewById(R.id.switch1);



        srvSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true) {
                    startService(new Intent(getApplication(), alarmBackground.class));
                    Toast toast=Toast.makeText(getApplicationContext(),"Services Started",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    stopService(new Intent(getApplication(), alarmBackground.class));
                    Toast toast=Toast.makeText(getApplicationContext(),"Services Stopped",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });







            }

    }

