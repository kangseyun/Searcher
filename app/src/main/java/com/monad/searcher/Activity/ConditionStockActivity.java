package com.monad.searcher.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.monad.searcher.Adapter.StockRecyclerViewAdapter;
import com.monad.searcher.Model.ConditionDetailModel;
import com.monad.searcher.Model.ConditionModel;
import com.monad.searcher.Model.Fragment2Model;
import com.monad.searcher.Model.StockData;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.ConditionDetail;
import com.monad.searcher.Util.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConditionStockActivity extends AppCompatActivity {

    private StockRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ArrayList<StockData> myDataset;
    private Toolbar toolbar;
    private ImageView setting;
    private TextView title;
    private Button mnotice_on, mnotice_off;
    private Retrofit retrofit;
    private ConditionDetail conditionDetail;
    private int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_stock);
        Intent i = getIntent();
        num = i.getIntExtra("num", 0);
        Log.i("num", num+"");
        //getData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_stock);
        mnotice_on = (Button) findViewById(R.id.notice_on);
        mnotice_off = (Button) findViewById(R.id.notice_off);
        setRecyclerView();
        setNoticeOn();
        setNoticeOff();
        setToolbar();
        getData();
    }

    private void setRecyclerView()
    {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new StockRecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new StockRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getApplicationContext(), ConditionStockDetailActivity.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

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
                Intent i = new Intent(ConditionStockActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    private void setNoticeOn() {
        mnotice_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ConditionStockActivity.this);

                alert.setTitle("알림을 받으시겠습니까?");
                alert.setMessage(getString(R.string.notice_on));

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });
    }

    private void getData() {
        retrofit = RetrofitService.getInstnace();
        conditionDetail = retrofit.create(ConditionDetail.class);

        Call<List<ConditionDetailModel>> load = conditionDetail.getConditionList(num);

        load.enqueue(new Callback<List<ConditionDetailModel>>() {
            @Override
            public void onResponse(Call<List<ConditionDetailModel>> call, Response<List<ConditionDetailModel>> response) {
                List<ConditionDetailModel> data = response.body();
                for(ConditionDetailModel obj : data) {
                    myDataset.add(new StockData(obj.getItem_name(),"+25.37%"));
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ConditionDetailModel>> call, Throwable t) {
                Log.i("fail",t.getMessage());
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

    private void setNoticeOff()
    {
        mnotice_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ConditionStockActivity.this);

                alert.setTitle("알림을 끄시겠습니까?");
                alert.setMessage(getString(R.string.notice_off));

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });
    }
}
