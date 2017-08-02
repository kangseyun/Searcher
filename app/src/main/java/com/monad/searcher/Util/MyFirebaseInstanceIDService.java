package com.monad.searcher.Util;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.monad.searcher.Model.TokenModel;
import com.monad.searcher.Retrofit.PushToken;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    private Realm realm;
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        databaseInit();

        String token = FirebaseInstanceId.getInstance().getToken();

        realm.beginTransaction();
        TokenModel tokenModel = realm.createObject(TokenModel.class); // 새 객체 만들기
        tokenModel.setToken(token);
        realm.commitTransaction();
    }


    private void databaseInit() {
        realm = Realm.getDefaultInstance();
    }
}