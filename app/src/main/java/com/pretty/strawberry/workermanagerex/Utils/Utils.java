package com.pretty.strawberry.workermanagerex.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.pretty.strawberry.workermanagerex.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Utils {

    public static final int NOTIFICATIONS_ID = 22;
    private static final String CHANNEL_ID = "notify";
    private static final String CHANNEL_NAME = "workmanager-reminder";

    public static void sendNotification(Context context)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            channel1.enableVibration(true);
            channel1.enableLights(true);
            channel1.setLightColor(R.color.colorPrimary);
            channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            notificationManager.createNotificationChannel(channel1);
        }

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentTitle("workManger Sample")
                .setContentText("WorkManager Started")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground);

        notificationManager.notify(1,builder.build());

    }
}
