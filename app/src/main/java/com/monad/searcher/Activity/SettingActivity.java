package com.monad.searcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.monad.searcher.Model.BasicStockModel;
import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.TokenModel;
import com.monad.searcher.Model.TokenPushModel;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.BasicStock;
import com.monad.searcher.Retrofit.PushToken;
import com.monad.searcher.Util.RetrofitService;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SettingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title;
    private Button mlogin;
    private CheckBox checkbox;
    private Realm realm;
    private Retrofit retrofit;
    private PushToken pushToken;
    private String token, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setToolbar();

        databaseInit();

        retrofit = RetrofitService.getInstnace();
        pushToken = retrofit.create(PushToken.class);
        RealmQuery<LoginData> query2 = realm.where(LoginData.class);
        RealmQuery<TokenModel> query = realm.where(TokenModel.class);


        RealmResults<TokenModel> result1 = query.findAll();
        RealmResults<LoginData> result2 = query2.findAll();
        if(result1.size() != 0) {
            token = result1.get(0).getToken();
        }
        email  = result2.get(0).getEmail();

        mlogin = (Button) findViewById(R.id.login);
        checkbox = (CheckBox) findViewById(R.id.push_checkbox);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Call<TokenPushModel> load = pushToken.pushToken(token, email);

                    load.enqueue(new Callback<TokenPushModel>() {
                        @Override
                        public void onResponse(Call<TokenPushModel> call, Response<TokenPushModel> response) {
                            TokenPushModel data = response.body();
                            Log.i("data", data.getStatus() + "");
                        }

                        @Override
                        public void onFailure(Call<TokenPushModel> call, Throwable t) {
                            Log.i("fail",t.getMessage());
                        }
                    });
                } else {
                    Call<TokenPushModel> load = pushToken.pushToken("no", email);

                    load.enqueue(new Callback<TokenPushModel>() {
                        @Override
                        public void onResponse(Call<TokenPushModel> call, Response<TokenPushModel> response) {
                            TokenPushModel data = response.body();
                            Log.i("data", data.getStatus() + "");
                        }

                        @Override
                        public void onFailure(Call<TokenPushModel> call, Throwable t) {
                            Log.i("fail",t.getMessage());
                        }
                    });
                }

            }
        });


        setLogin();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText("설정");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setLogin(){
        mlogin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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
