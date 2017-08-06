package com.monad.searcher;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.monad.searcher.Activity.LoginActivity;
import com.monad.searcher.Activity.MainActivity;
import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.LoginSingleton;
import com.monad.searcher.Util.RealmManager;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.internal.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        final TextView statusMessage = (TextView)findViewById(R.id.introStatusMessage);

        databaseInit();


        RealmManager.open();
        final RealmResults<LoginData> result = RealmManager.CreateLoginDao().loadAll();
        List<LoginData> data = result;

        if(data.size() == 0) {
            LoginSingleton login = LoginSingleton.getInstance();
            login.setToken("invalid_token");

            Callback<LoginData> callback = new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                            intent.putExtra("code", 255);
                            startActivity(intent);

                            finish();
                        }
                    }, 2000);
                }

                @Override
                public void onFailure(Call<LoginData> call, Throwable t) {
                    statusMessage.setText("인터넷 연결을 확인해주십시오.");
                    call.clone().enqueue(this);
                }
            };

            login.checkInvalidToken(callback);

        } else {
            LoginSingleton login = LoginSingleton.getInstance();

            login.setToken(data.get(0).getToken());
            login.setEmail(data.get(0).getEmail());

            Callback<LoginData> callback = new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    LoginData data = response.body();

                    if(!data.getLoginStatus().equals("valid_token")) {
                        RealmManager.CreateLoginDao().remove(result.get(0));

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                                intent.putExtra("code", 255);
                                startActivity(intent);

                                finish();
                            }
                        }, 2000);

                    }
                    else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                startActivity(intent);

                                finish();
                            }
                        }, 2000);
                    }
                }

                @Override
                public void onFailure(Call<LoginData> call, Throwable t) {
                    statusMessage.setText("인터넷 연결을 확인해주십시오.");

                    call.clone().enqueue(this);
                }
            };

            login.checkInvalidToken(callback);

        }
    }

    public void databaseInit() {
        Realm.init(this);

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("realm-sample.db")
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        Realm realm = Realm.getDefaultInstance();
    }

}
