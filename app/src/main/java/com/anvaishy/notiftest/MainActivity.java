package com.anvaishy.notiftest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    void makeNotificationChannel(String id, String name, int importance)
    {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setShowBadge(true); // set false to disable badges, Oreo exclusive

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }
    void issueNotification(String title, String text)
    {

        // make the channel. The method has been discussed before.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeNotificationChannel("CHANNEL_1", "Example channel", NotificationManager.IMPORTANCE_DEFAULT);
        }
        // the check ensures that the channel will only be made
        // if the device is running Android 8+

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this, "CHANNEL_1");
        // the second parameter is the channel id.
        // it should be the same as passed to the makeNotificationChannel() method

        notification
                .setSmallIcon(R.mipmap.ic_launcher) // can use any other icon
                .setContentTitle(title)
                .setContentText(text)
                .setNumber(3); // this shows a number in the notification dots

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(1, notification.build());
        // it is better to not use 0 as notification id, so used 1.
    }
    EditText a,b;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.editTextTextPersonName);
        b = findViewById(R.id.editTextTextPersonName2);
    }

    public void Notif(View view) {
            String title = a.getText().toString();
            String text = b.getText().toString();
        issueNotification(title,text);
    }
}
/*
*
* int importance: This is the importance level of the channel. The levels are as follows:
NotificationManager.IMPORTANCE_MIN – shows only in notification shade, no sound or peek.
NotificationManager.IMPORTANCE_LOW – shows everywhere, doesn’t make sound, doesn’t peek.
NotificationManager.IMPORTANCE_DEFAULT – shows everywhere, makes sound but doesn’t peek.
NotificationManager.IMPORTANCE_HIGH – shows everywhere, makes sound and peeks (visual interruption).
NotificationManager.IMPORTANCE_MAX – this importance level is usually not used. Works similar to IMPORTANCE_HIGH.
*
* */