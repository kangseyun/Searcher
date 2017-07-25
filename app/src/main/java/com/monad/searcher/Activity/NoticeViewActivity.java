package com.monad.searcher.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.monad.searcher.Adapter.PagerAdapter;
import com.monad.searcher.Model.CommunityModel;
import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.LoginSingleton;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.Community;
import com.monad.searcher.Util.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class NoticeViewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private TextView title;
    private TextView article_title, article_content;

    private Button deleteButton;
    private AlertDialog deleteDialog;

    private ImageView setting;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);
        setToolbar();

        Intent intent = getIntent();
        index = intent.getIntExtra("n", -1);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.deleteDialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteArticle(index);
            }
        });

        builder.setMessage(R.string.deleteDialog_content).setTitle(R.string.deleteDialog_title);

        deleteDialog = builder.create();

        article_title = (TextView)findViewById(R.id.title);
        article_content = (TextView)findViewById(R.id.contents);
        deleteButton = (Button)findViewById(R.id.btn_deleteArticle);

        deleteButton.setOnClickListener(this);


        getArticle(index);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_deleteArticle:
                deleteDialog.show();
                break;
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.toolbar_title);
        setting = (ImageView) findViewById(R.id.setting);
        title.setText("써처");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NoticeViewActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void getArticle(Integer index) {
        Retrofit retrofit;
        Community community;

        retrofit = RetrofitService.getInstnace();
        community = retrofit.create(Community.class);

        LoginSingleton login = LoginSingleton.getInstance();

        Call<CommunityModel> load = community.getArticle(index, login.getToken());

        load.enqueue(new Callback<CommunityModel>() {
            @Override
            public void onResponse(Call<CommunityModel> call, Response<CommunityModel> response) {
                if(response.isSuccessful()) {
                    CommunityModel data = response.body();

                    try {
                        if (data.getStatus().equals("self"))
                            deleteButton.setVisibility(View.VISIBLE);
                    } catch (Exception e) {

                    }

                    article_title.setText(data.getContent());
                    article_content.setText(data.getContent());
                }
            }

            @Override
            public void onFailure(Call<CommunityModel> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

    public void deleteArticle(Integer index) {
        Retrofit retrofit;
        Community community;

        retrofit = RetrofitService.getInstnace();
        community = retrofit.create(Community.class);

        LoginSingleton login = LoginSingleton.getInstance();

        Call<CommunityModel> load = community.deleteArticle(index, login.getToken());

        load.enqueue(new Callback<CommunityModel>() {
            @Override
            public void onResponse(Call<CommunityModel> call, Response<CommunityModel> response) {
                if(response.isSuccessful()) {
                }
            }

            @Override
            public void onFailure(Call<CommunityModel> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });


    }
}
