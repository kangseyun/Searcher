package com.monad.searcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.monad.searcher.Adapter.StockRecyclerViewAdapter;
import com.monad.searcher.Model.StockData;
import com.monad.searcher.R;

import java.util.ArrayList;

public class ConditionStockActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ArrayList<StockData> myDataset;
    private Toolbar toolbar;
    private ImageView setting;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_stock);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_stock);
        setRecyclerView();
        setToolbar();
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

        myDataset.add(new StockData("현재가"));
        myDataset.add(new StockData("등락률"));
        myDataset.add(new StockData("거래량"));
        myDataset.add(new StockData("편입가"));
        myDataset.add(new StockData("편익대비수익률"));
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
}
