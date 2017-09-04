package com.monad.searcher.Util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.On;
import com.github.nkzawa.socketio.client.Socket;
import com.monad.searcher.Activity.MainActivity;
import com.monad.searcher.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by seyun on 2017. 9. 4..
 */

public class NotificationService extends Service {
    public NotificationService() {

    }



    @Override
    public void onCreate(){
        mSocket.on("message", onNewMessage);
        mSocket.connect();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("socket", "open");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i("socket", "open");
        return null;
    }

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://115.68.122.248:3000");
            Log.i("SocketOpen", "Open");
        } catch (URISyntaxException e) {}
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i("response", "response");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    try {
                        message = data.getString("message");
                        Log.i("message", message);
                        sendNotification(message);
                    } catch (JSONException e) {
                        return;
                    }

                }
            }, 0);
        }
    };

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

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("message", onNewMessage);
    }
}
