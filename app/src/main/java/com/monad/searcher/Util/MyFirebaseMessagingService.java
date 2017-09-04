package com.monad.searcher.Util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.monad.searcher.Activity.MainActivity;
import com.monad.searcher.Model.NotificationModel;
import com.monad.searcher.Model.TokenCheckModel;
import com.monad.searcher.R;

import io.realm.Realm;
import retrofit2.Retrofit;


public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    private Realm realm;
    private Retrofit retrofit;
    private NotificationModel notificationModel;


    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        realm = Realm.getDefaultInstance();

        String content = remoteMessage.getData().get("message").toString();

        realm.beginTransaction();
        notificationModel = realm.createObject(NotificationModel.class); // 새 객체 만들기
        notificationModel.setContent(content);
        realm.commitTransaction();


        sendNotification(content);
        Log.i("Received", "call");
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.searcher_icon)
                .setContentTitle("써처 알림")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
