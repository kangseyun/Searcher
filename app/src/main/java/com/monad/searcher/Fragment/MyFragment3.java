package com.monad.searcher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monad.searcher.Activity.Fragment3DetailActivity;
import com.monad.searcher.Dialog.LodingDialog;
import com.monad.searcher.Model.MyData;
import com.monad.searcher.Model.RankModel;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.Rank;
import com.monad.searcher.Util.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyFragment3 extends Fragment{
    private View v;
    private TextView title, kospi_down_point1,kospi_down_point2,kospi_down_point3,kospi_down_point4,kospi_down_point5;
    private TextView kospi_down_name1,kospi_down_name2, kospi_down_name3, kospi_down_name4, kospi_down_name5;
    private TextView kospi_top_point1,kospi_top_point2,kospi_top_point3,kospi_top_point4,kospi_top_point5;
    private TextView kospi_top_name1,kospi_top_name2,kospi_top_name3,kospi_top_name4,kospi_top_name5;
    private TextView kosdaq_down_point1,kosdaq_down_point2,kosdaq_down_point3,kosdaq_down_point4,kosdaq_down_point5;
    private TextView kosdaq_down_name1,kosdaq_down_name2, kosdaq_down_name3, kosdaq_down_name4, kosdaq_down_name5;
    private TextView kosdaq_top_point1,kosdaq_top_point2,kosdaq_top_point3,kosdaq_top_point4, kosdaq_top_point5;
    private TextView kosdaq_top_name1,kosdaq_top_name2,kosdaq_top_name3,kosdaq_top_name4,kosdaq_top_name5;
    private Retrofit retrofit;
    private Rank rank;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_3, container, false);
        setLayout();
        getData();
        return v;
    }

    private void setLayout() {
        kospi_top_name1 = (TextView) v.findViewById(R.id.kospi_top_name1);
        kospi_top_name2 = (TextView) v.findViewById(R.id.kospi_top_name2);
        kospi_top_name3 = (TextView) v.findViewById(R.id.kospi_top_name3);
        kospi_top_name4 = (TextView) v.findViewById(R.id.kospi_top_name4);
        kospi_top_name5 = (TextView) v.findViewById(R.id.kospi_top_name5);
        kospi_top_point1 = (TextView) v.findViewById(R.id.kospi_top_point1);
        kospi_top_point2 = (TextView) v.findViewById(R.id.kospi_top_point2);
        kospi_top_point3 = (TextView) v.findViewById(R.id.kospi_top_point3);
        kospi_top_point4 = (TextView) v.findViewById(R.id.kospi_top_point4);
        kospi_top_point5 = (TextView) v.findViewById(R.id.kospi_top_point5);

        kospi_down_name1 = (TextView) v.findViewById(R.id.kospi_down_name1);
        kospi_down_name2 = (TextView) v.findViewById(R.id.kospi_down_name2);
        kospi_down_name3 = (TextView) v.findViewById(R.id.kospi_down_name3);
        kospi_down_name4 = (TextView) v.findViewById(R.id.kospi_down_name4);
        kospi_down_name5 = (TextView) v.findViewById(R.id.kospi_down_name5);
        kospi_down_point1 = (TextView) v.findViewById(R.id.kospi_down_point1);
        kospi_down_point2 = (TextView) v.findViewById(R.id.kospi_down_point2);
        kospi_down_point3 = (TextView) v.findViewById(R.id.kospi_down_point3);
        kospi_down_point4 = (TextView) v.findViewById(R.id.kospi_down_point4);
        kospi_down_point5 = (TextView) v.findViewById(R.id.kospi_down_point5);

        kosdaq_top_name1 = (TextView) v.findViewById(R.id.kosdaq_top_name1);
        kosdaq_top_name2 = (TextView) v.findViewById(R.id.kosdaq_top_name2);
        kosdaq_top_name3 = (TextView) v.findViewById(R.id.kosdaq_top_name3);
        kosdaq_top_name4 = (TextView) v.findViewById(R.id.kosdaq_top_name4);
        kosdaq_top_name5 = (TextView) v.findViewById(R.id.kosdaq_top_name5);
        kosdaq_top_point1 = (TextView) v.findViewById(R.id.kosdaq_top_point1);
        kosdaq_top_point2 = (TextView) v.findViewById(R.id.kosdaq_top_point2);
        kosdaq_top_point3 = (TextView) v.findViewById(R.id.kosdaq_top_point3);
        kosdaq_top_point4 = (TextView) v.findViewById(R.id.kosdaq_top_point4);
        kosdaq_top_point5 = (TextView) v.findViewById(R.id.kosdaq_top_point5);

        kosdaq_down_name1 = (TextView) v.findViewById(R.id.kosdaq_down_name1);
        kosdaq_down_name2 = (TextView) v.findViewById(R.id.kosdaq_down_name2);
        kosdaq_down_name3 = (TextView) v.findViewById(R.id.kosdaq_down_name3);
        kosdaq_down_name4 = (TextView) v.findViewById(R.id.kosdaq_down_name4);
        kosdaq_down_name5 = (TextView) v.findViewById(R.id.kosdaq_down_name5);
        kosdaq_down_point1 = (TextView) v.findViewById(R.id.kosdaq_down_point1);
        kosdaq_down_point2 = (TextView) v.findViewById(R.id.kosdaq_down_point2);
        kosdaq_down_point3 = (TextView) v.findViewById(R.id.kosdaq_down_point3);
        kosdaq_down_point4 = (TextView) v.findViewById(R.id.kosdaq_down_point4);
        kosdaq_down_point5 = (TextView) v.findViewById(R.id.kosdaq_down_point5);
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