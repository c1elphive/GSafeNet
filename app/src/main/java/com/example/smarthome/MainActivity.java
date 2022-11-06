package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.HttpURLConnection;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_1_ID ="channel1";
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);

        final ImageButton get_button =(ImageButton) findViewById(R.id.ImageButton1);// ...
        get_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         sendGetRequest();
         showNotification();
        }
    });

        final Button srvButton = (Button) findViewById(R.id.srvbtn);
        srvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AlarmService.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    public void sendGetRequest() {
        final TextView textView = (TextView) findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://8610-2a02-587-dc03-4d15-c9f-5bf9-f9fb-d078.ngrok.io/arduino/readGas/1";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response);
                        textView.setText("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);
        queue.start();

    }
    public void showNotification(){
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.fireicon)
                .setContentTitle("Hello")
                .setContentText("Whate are u doing step bro")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManager.notify(1,notification);
    }
    private void createNotificationChannel() {



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
}
