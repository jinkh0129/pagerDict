package com.sungdonggu.pagerdict.Java.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.sungdonggu.pagerdict.R;

public class JavaNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_notification);
    }

    public void createNotification(View view) {
        show();
    }


    public void show() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        // Oreo는 ChannelID를 꼭 설정해줘야한다.

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("알림 제목");
        builder.setContentText("알림 세부 텍스트");

        Intent intent = new Intent(this, JavaNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Notification을 클릭했을 때 pendingIntent가 실행된다는 의미
        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);
        builder.setColor(Color.RED);

        // Notification이 소리도 나게 할 수 있다.
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        // 진동
        long[] vibrate = {0, 100, 200, 300}; // 진동의 규칙이다.(밀리세컨)
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true); // Notification을 클릭하게되면 Noti가 날아가게 할 것인가? True는 날라가게 할 것이다.

        // Oreo는 ChannelID를 등록해야한다.
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 버전분기...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        manager.notify(1, builder.build());

    }

    public void removeNotification(View view) {
        hide();
    }

    public void hide() {
        NotificationManagerCompat.from(this).cancel(1);
    }
}