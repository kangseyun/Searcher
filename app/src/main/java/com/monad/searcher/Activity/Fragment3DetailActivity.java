package com.monad.searcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.monad.searcher.Dialog.HelpDialog;
import com.monad.searcher.Dialog.LodingDialog;
import com.monad.searcher.Model.ConditionDetailModel;
import com.monad.searcher.Model.RankModel;
import com.monad.searcher.Model.StockData;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.ConditionDetail;
import com.monad.searcher.Retrofit.Rank;
import com.monad.searcher.Util.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Fragment3DetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title, kospi_down_point1,kospi_down_point2,kospi_down_point3,kospi_down_point4,kospi_down_point5;
    private TextView kospi_down_name1,kospi_down_name2, kospi_down_name3, kospi_down_name4, kospi_down_name5;
    private TextView kospi_top_point1,kospi_top_point2,kospi_top_point3,kospi_top_point4,kospi_top_point5;
    private TextView kospi_top_name1,kospi_top_name2,kospi_top_name3,kospi_top_name4,kospi_top_name5;
    private TextView kosdaq_down_point1,kosdaq_down_point2,kosdaq_down_point3,kosdaq_down_point4,kosdaq_down_point5;
    private TextView kosdaq_down_name1,kosdaq_down_name2, kosdaq_down_name3, kosdaq_down_name4, kosdaq_down_name5;
    private TextView kosdaq_top_point1,kosdaq_top_point2,kosdaq_top_point3,kosdaq_top_point4, kosdaq_top_point5;
    private TextView kosdaq_top_name1,kosdaq_top_name2,kosdaq_top_name3,kosdaq_top_name4,kosdaq_top_name5;
    private ImageView setting;
    private Retrofit retrofit;
    private Rank rank;
    private LodingDialog lodingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment3_detail);
        setToolbar();
        getData();
        setLayout();
        setHelp();
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
                Intent i = new Intent(Fragment3DetailActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    private void setLayout() {
        kospi_top_name1 = (TextView) findViewById(R.id.kospi_top_name1);
        kospi_top_name2 = (TextView) findViewById(R.id.kospi_top_name2);
        kospi_top_name3 = (TextView) findViewById(R.id.kospi_top_name3);
        kospi_top_name4 = (TextView) findViewById(R.id.kospi_top_name4);
        kospi_top_name5 = (TextView) findViewById(R.id.kospi_top_name5);
        kospi_top_point1 = (TextView) findViewById(R.id.kospi_top_point1);
        kospi_top_point2 = (TextView) findViewById(R.id.kospi_top_point2);
        kospi_top_point3 = (TextView) findViewById(R.id.kospi_top_point3);
        kospi_top_point4 = (TextView) findViewById(R.id.kospi_top_point4);
        kospi_top_point5 = (TextView) findViewById(R.id.kospi_top_point5);

        kospi_down_name1 = (TextView) findViewById(R.id.kospi_down_name1);
        kospi_down_name2 = (TextView) findViewById(R.id.kospi_down_name2);
        kospi_down_name3 = (TextView) findViewById(R.id.kospi_down_name3);
        kospi_down_name4 = (TextView) findViewById(R.id.kospi_down_name4);
        kospi_down_name5 = (TextView) findViewById(R.id.kospi_down_name5);
        kospi_down_point1 = (TextView) findViewById(R.id.kospi_down_point1);
        kospi_down_point2 = (TextView) findViewById(R.id.kospi_down_point2);
        kospi_down_point3 = (TextView) findViewById(R.id.kospi_down_point3);
        kospi_down_point4 = (TextView) findViewById(R.id.kospi_down_point4);
        kospi_down_point5 = (TextView) findViewById(R.id.kospi_down_point5);

        kosdaq_top_name1 = (TextView) findViewById(R.id.kosdaq_top_name1);
        kosdaq_top_name2 = (TextView) findViewById(R.id.kosdaq_top_name2);
        kosdaq_top_name3 = (TextView) findViewById(R.id.kosdaq_top_name3);
        kosdaq_top_name4 = (TextView) findViewById(R.id.kosdaq_top_name4);
        kosdaq_top_name5 = (TextView) findViewById(R.id.kosdaq_top_name5);
        kosdaq_top_point1 = (TextView) findViewById(R.id.kosdaq_top_point1);
        kosdaq_top_point2 = (TextView) findViewById(R.id.kosdaq_top_point2);
        kosdaq_top_point3 = (TextView) findViewById(R.id.kosdaq_top_point3);
        kosdaq_top_point4 = (TextView) findViewById(R.id.kosdaq_top_point4);
        kosdaq_top_point5 = (TextView) findViewById(R.id.kosdaq_top_point5);

        kosdaq_down_name1 = (TextView) findViewById(R.id.kosdaq_down_name1);
        kosdaq_down_name2 = (TextView) findViewById(R.id.kosdaq_down_name2);
        kosdaq_down_name3 = (TextView) findViewById(R.id.kosdaq_down_name3);
        kosdaq_down_name4 = (TextView) findViewById(R.id.kosdaq_down_name4);
        kosdaq_down_name5 = (TextView) findViewById(R.id.kosdaq_down_name5);
        kosdaq_down_point1 = (TextView) findViewById(R.id.kosdaq_down_point1);
        kosdaq_down_point2 = (TextView) findViewById(R.id.kosdaq_down_point2);
        kosdaq_down_point3 = (TextView) findViewById(R.id.kosdaq_down_point3);
        kosdaq_down_point4 = (TextView) findViewById(R.id.kosdaq_down_point4);
        kosdaq_down_point5 = (TextView) findViewById(R.id.kosdaq_down_point5);
    }

    private void setHelp() {
        lodingDialog = new LodingDialog(this);
        lodingDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    private void getData() {
        retrofit = RetrofitService.getInstnace();
        rank = retrofit.create(Rank.class);

        Call<RankModel> load = rank.getRank();

        load.enqueue(new Callback<RankModel>() {
            @Override
            public void onResponse(Call<RankModel> call, Response<RankModel> response) {
                RankModel data = response.body();
                kospi_top_name1.setText(data.getName().get(0));
                kospi_top_name2.setText(data.getName().get(1));
                kospi_top_name3.setText(data.getName().get(2));
                kospi_top_name4.setText(data.getName().get(3));
                kospi_top_name5.setText(data.getName().get(4));
                kospi_top_point1.setText(data.getName2().get(0));
                kospi_top_point2.setText(data.getName2().get(1));
                kospi_top_point3.setText(data.getName2().get(2));
                kospi_top_point4.setText(data.getName2().get(3));
                kospi_top_point5.setText(data.getName2().get(4));
                lodingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RankModel> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });

        Call<RankModel> load2 = rank.getRankDown();

        load2.enqueue(new Callback<RankModel>() {
            @Override
            public void onResponse(Call<RankModel> call, Response<RankModel> response) {
                RankModel data = response.body();
                kospi_down_name1.setText(data.getName().get(0));
                kospi_down_name2.setText(data.getName().get(1));
                kospi_down_name3.setText(data.getName().get(2));
                kospi_down_name4.setText(data.getName().get(3));
                kospi_down_name5.setText(data.getName().get(4));
                kospi_down_point1.setText(data.getName2().get(0));
                kospi_down_point2.setText(data.getName2().get(1));
                kospi_down_point3.setText(data.getName2().get(2));
                kospi_down_point4.setText(data.getName2().get(3));
                kospi_down_point5.setText(data.getName2().get(4));
            }

            @Override
            public void onFailure(Call<RankModel> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });

        Call<RankModel> load3 = rank.getKosdaqRank();

        load3.enqueue(new Callback<RankModel>() {
            @Override
            public void onResponse(Call<RankModel> call, Response<RankModel> response) {
                RankModel data = response.body();
                kosdaq_top_name1.setText(data.getName().get(0));
                kosdaq_top_name2.setText(data.getName().get(1));
                kosdaq_top_name3.setText(data.getName().get(2));
                kosdaq_top_name4.setText(data.getName().get(3));
                kosdaq_top_name5.setText(data.getName().get(4));
                kosdaq_top_point1.setText(data.getName2().get(0));
                kosdaq_top_point2.setText(data.getName2().get(1));
                kosdaq_top_point3.setText(data.getName2().get(2));
                kosdaq_top_point4.setText(data.getName2().get(3));
                kosdaq_top_point5.setText(data.getName2().get(4));
            }

            @Override
            public void onFailure(Call<RankModel> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });

        Call<RankModel> load4 = rank.getKosdaqRankDown();

        load4.enqueue(new Callback<RankModel>() {
            @Override
            public void onResponse(Call<RankModel> call, Response<RankModel> response) {
                RankModel data = response.body();
                kosdaq_down_name1.setText(data.getName().get(0));
                kosdaq_down_name2.setText(data.getName().get(1));
                kosdaq_down_name3.setText(data.getName().get(2));
                kosdaq_down_name4.setText(data.getName().get(3));
                kosdaq_down_name5.setText(data.getName().get(4));
                kosdaq_down_point1.setText(data.getName2().get(0));
                kosdaq_down_point2.setText(data.getName2().get(1));
                kosdaq_down_point3.setText(data.getName2().get(2));
                kosdaq_down_point4.setText(data.getName2().get(3));
                kosdaq_down_point5.setText(data.getName2().get(4));


            }

            @Override
            public void onFailure(Call<RankModel> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });
    }
}