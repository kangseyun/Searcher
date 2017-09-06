package com.monad.searcher.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.monad.searcher.Adapter.NoticeBoardRecyclerViewAdapter;
import com.monad.searcher.Adapter.NotificationRecyclerViewAdapter;
import com.monad.searcher.Adapter.StockRecyclerViewAdapter;
import com.monad.searcher.Model.NotificationModel;
import com.monad.searcher.Model.TokenModel;
import com.monad.searcher.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NotificationRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList myDataset = new ArrayList<NotificationModel>();
    private Realm realm;
    private Toolbar toolbar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        realm = Realm.getDefaultInstance();
        setToolbar();

        setRecyclerView();
        getData();
    }

    private void setRecyclerView()
    {

        mRecyclerView = (RecyclerView) findViewById(R.id.notification_RecyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new NotificationRecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NotificationRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    private void getData() {
        RealmQuery<NotificationModel> query = realm.where(NotificationModel.class);
        RealmResults<NotificationModel> result = query.findAll();

        for(int i = 1; i<20; i++) {
            try {
                myDataset.add(result.get(result.size() - i));
            } catch (Exception e) {
                break;
            }
        }

        mAdapter.notifyDataSetChanged();
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
}
