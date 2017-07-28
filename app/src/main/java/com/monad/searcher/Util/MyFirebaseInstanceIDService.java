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
        // Get updated InstanceID token.
        databaseInit();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed tokeon: " + token);

        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        realm.beginTransaction();
        TokenModel tokenModel = realm.createObject(TokenModel.class); // 새 객체 만들기
        tokenModel.setToken(token);
        realm.commitTransaction();
    }


    private void databaseInit() {
        Realm.init(this);

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("realm-sample.db")
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getDefaultInstance();
    }
}