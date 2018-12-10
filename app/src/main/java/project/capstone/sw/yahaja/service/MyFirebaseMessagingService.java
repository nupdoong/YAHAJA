package project.capstone.sw.yahaja.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import project.capstone.sw.yahaja.LoginActivity;
import project.capstone.sw.yahaja.MapActivity;
import project.capstone.sw.yahaja.MatchActivity;
import project.capstone.sw.yahaja.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final static String TAG = "FCM_MESSAGE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            String body = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Notification Body: " + body);


            if (Build.VERSION.SDK_INT < 26) {
                return;
            }else {
                NotificationManager notificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationChannel channel = new NotificationChannel("default",
                        "Channel name",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("Channel description");
                notificationManager.createNotificationChannel(channel);

            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                    .setSmallIcon(R.mipmap.ic_launcher) // 알림 영역에 노출 될 아이콘.
                    .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                    .setContentText(body)
                    .setAutoCancel(true); // Firebase Console 에서 사용자가 전달한 메시지내용)

            if(body.equals("매칭이 완료 되었습니다.")){

                Log.d( "매칭이 완료 되었습니다", "매칭이 완료 되었습니다");

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(0, notificationBuilder.build());

                return;
            }else if(body.equals("매칭이 거절 되었습니다.")){

                Log.d( "매칭이 거절 되었습니다.", "매칭이 거절 되었습니다.");

                Intent resultIntent = new Intent(this, MapActivity.class);

                // Creates the PendingIntent
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                notificationBuilder.setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(0, notificationBuilder.build());

                return;
            }


            String[] tmp = body.split(":");
            String matchId = tmp[0];

            Intent resultIntent = new Intent(this, MatchActivity.class);

            Log.d( "matchIdmatchId", matchId);

            resultIntent.putExtra("matchId", matchId);

            // Creates the PendingIntent
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );

            notificationBuilder.setContentIntent(pendingIntent);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(0, notificationBuilder.build());
        }
    }
}
