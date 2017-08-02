package com.monad.searcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.monad.searcher.Model.CommunityModel;
import com.monad.searcher.Model.LoginSingleton;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.Community;
import com.monad.searcher.Util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NoticeBoardActivity extends AppCompatActivity {
    private Button mWrite;
    private EditText mtitle;
    private Toolbar toolbar;
    private ImageView setting;

    private TextView title;
    private TextView contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard);
        mWrite = (Button) findViewById(R.id.btn_noticeboard);

        mtitle = (EditText) findViewById(R.id.title);
        contents = (EditText) findViewById(R.id.contents);

        setToolbar();
        setWrite();
    }

    private void setWrite()
    {
        mWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleString = mtitle.getText().toString();
                String contentString = contents.getText().toString();

                postArticle(titleString, contentString);
            }
        });
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
                Intent i = new Intent(NoticeBoardActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void postArticle(String title, String content) {
        Retrofit retrofit;
        Community community;

        LoginSingleton login = LoginSingleton.getInstance();

        retrofit = RetrofitService.getInstnace();
        community = retrofit.create(Community.class);

        Call<CommunityModel> load = community.postArticles(title, content, login.getToken());

        load.enqueue(new Callback<CommunityModel>() {
            @Override
            public void onResponse(Call<CommunityModel> call, Response<CommunityModel> response) {
                finish();
            }

            @Override
            public void onFailure(Call<CommunityModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.postArticle_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}