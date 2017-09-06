package com.monad.searcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.monad.searcher.Adapter.PagerAdapter;
import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.LoginSingleton;
import com.monad.searcher.Model.PushModel;
import com.monad.searcher.Model.TokenCheckModel;
import com.monad.searcher.Model.TokenModel;
import com.monad.searcher.R;
import com.monad.searcher.Util.NotificationService;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;
    private ImageView setting;
    private Realm realm;
    private Intent notification;
    private PushModel pushModel;
    private TokenCheckModel checkResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();
        init();
        realm = Realm.getDefaultInstance();
        startService();

        Thread checkTokenThread = new Thread() {
            LoginSingleton login = LoginSingleton.getInstance();

            Callback<LoginData> callback = new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    LoginData data = response.body();

                    if(data.getLoginStatus().equals("invalid_token")) {
                        if(LoginActivity.active == false) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.putExtra("code", 254);
                            startActivity(intent);
                        }
                    } else{

                    }
                }

                @Override
                public void onFailure(Call<LoginData> call, Throwable t) {

                }

            };

            @Override
            public void run() {
                while(true) {
                    login.checkInvalidToken(callback);
                    try {
                        Thread.sleep(60000);
                    } catch(Exception e) {

                    }
                }
            }
        };

        checkTokenThread.start();
    }

    void init() {
        setToolbar();
        tabBind();
        setViewPager();
    }

    private void startService() {
        final LoginSingleton login = LoginSingleton.getInstance();
        Callback<LoginData> callback = new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                LoginData data = response.body();
                login.setPush(data.getPush());
                Log.i("push", data.getPush());
                RealmQuery<PushModel> query = realm.where(PushModel.class);
                RealmResults<PushModel> result = query.findAll();

                if(result.size() == 0) {
                    realm.beginTransaction();
                    PushModel pushObj = realm.createObject(PushModel.class); // 새 객체 만들기
                    pushObj.setPush(data.getPush());
                    realm.commitTransaction();
                }
                else {
                    realm.beginTransaction();
                    pushModel = result.get(0);
                    pushModel.setPush(data.getPush());
                    realm.commitTransaction();
                }

                RealmQuery<TokenCheckModel> check = realm.where(TokenCheckModel.class);
                try {
                     checkResult = check.findAll().first();
                } catch (Exception e) {
                    realm.beginTransaction();
                    checkResult = realm.createObject(TokenCheckModel.class); // 새 객체 만들기
                    checkResult.setCheck(false);
                    realm.commitTransaction();
                }

                notification = new Intent(MainActivity.this, NotificationService.class);
                LoginSingleton.getInstance().setFlag(checkResult.isCheck());
                startService(notification);
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {

            }

        };

        login.checkInvalidToken(callback);
    }
    void tabBind() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabs();
        tabLayout.setOnTabSelectedListener(this);
    }

    void selectPage(int pageIndex)
    {
        viewPager.setCurrentItem(pageIndex);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(adapter);
    }

    private void setupTabs() {
        // Title Setup
        tabLayout.addTab(tabLayout.newTab().setText("소식"));
        tabLayout.addTab(tabLayout.newTab().setText("조건"));
        tabLayout.addTab(tabLayout.newTab().setText("증시 동향"));
        tabLayout.addTab(tabLayout.newTab().setText("커뮤니티"));
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.toolbar_title);
        setting = (ImageView) findViewById(R.id.setting);
        title.setText(R.string.app_name_ontab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(notification);
    }
}
