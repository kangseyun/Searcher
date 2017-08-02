package com.monad.searcher.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.monad.searcher.Model.ConditionStockModel;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.ConditionDetail;
import com.monad.searcher.Retrofit.ConditionStock;
import com.monad.searcher.Util.RetrofitService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConditionStockDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView setting;
    private String id;

    private TextView hight, down, before_cost, item_percentage, volume,
            market_value, item_current_price, item_code, cost;
    private TextView now_point, title;
    private Retrofit retrofit;
    private ConditionStock conditionStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_stock_detail);
        setToolbar();

        Intent i = getIntent();
        id  = i.getStringExtra("code");
        Log.i("id", id);
        hight = (TextView) findViewById(R.id.hight);
        down = (TextView) findViewById(R.id.down);
        before_cost = (TextView) findViewById(R.id.before_cost);
        item_percentage = (TextView) findViewById(R.id.item_percentage);
        market_value = (TextView) findViewById(R.id.market_value);
        now_point = (TextView) findViewById(R.id.now_point);
        title = (TextView) findViewById(R.id.stock_title);
        item_current_price = (TextView) findViewById(R.id.item_current_price);
        item_code = (TextView) findViewById(R.id.item_code);
        cost = (TextView) findViewById(R.id.cost);
        getData();
    }

    private void getData() {
        retrofit = RetrofitService.getInstnace();
        conditionStock = retrofit.create(ConditionStock.class);


        Call<ConditionStockModel> load = conditionStock.getConditionList(id);

        load.enqueue(new Callback<ConditionStockModel>() {
            @Override
            public void onResponse(Call<ConditionStockModel> call, Response<ConditionStockModel> response) {
                ConditionStockModel data = response.body();
                Log.i("data", data.getItem_code() + "");
                title.setText(data.getItem_name());
                now_point.setText(data.getItem_price()+ "");
                item_current_price.setText(data.getItem_current_price()+"");
                hight.setText(data.getItem_high_price() + "");
                down.setText(data.getItem_low_price() + "");
                market_value.setText(data.getItem_marketcap() + "");
                before_cost.setText(data.getItem_yester_price() + "");
                item_code.setText(data.getItem_code());
                item_percentage.setText(data.getItem_percentage() + "");
                cost.setText(data.getItem_price()+"");
                //setData(data.getItem_high_price(), data.getItem_low_price(), data.get);
            }

            @Override
            public void onFailure(Call<ConditionStockModel> call, Throwable t) {
                Log.i("fail",t.getMessage());
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
                Intent i = new Intent(ConditionStockDetailActivity.this, SettingActivity.class);
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
}
