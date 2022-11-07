package com.example.smarthome;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.os.Build;
import android.os.Handler;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.example.smarthome.MainActivity.CHANNEL_1_ID;
import static java.lang.Thread.sleep;


public class alarmBackground extends Service {
int n = 0;

String alarm2;
private NotificationManagerCompat notificationManager;

private static final String TAG ="alarmBackground";
final Handler handler  = new Handler();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                sendRequest();
                handler.postDelayed(this,1000);

            }
        };
        handler.postDelayed(r,2000);

        return START_STICKY;
    }

    public void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }

    public void sendRequest(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = " http://f199-2a02-587-7a01-e2b-a515-48ed-e28e-6163.ngrok.io/arduino/readsensors/32";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Toast toast=Toast.makeText(getApplicationContext()," "+response,Toast.LENGTH_SHORT);
//                        toast.show();
                        int nresponse=Integer.parseInt(response);
                        switch(nresponse){
                            case 1:
                                showNotification();
                                break;
                            case 2:
                                Toast toast=Toast.makeText(getApplicationContext(),"fire",Toast.LENGTH_SHORT);
                                toast.show();
                                break;


                            default:
                                //
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        queue.add(stringRequest);
        queue.start();
    }
    private void createNotificationChannel() {

         String CHANNEL_1_ID ="channel1";


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is my channel");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }
    public void showNotification(){
        notificationManager = NotificationManagerCompat.from(this);
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.fireicon)
                .setContentTitle("Alarm")
                .setContentText("Gas Leakage")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManager.notify(1,notification);
    }

    }

