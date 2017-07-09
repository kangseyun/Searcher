package com.monad.searcher.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monad.searcher.Activity.NoticeBoardActivity;
import com.monad.searcher.Adapter.NoticeBoardRecyclerViewAdapter;
import com.monad.searcher.Model.MyData2;
import com.monad.searcher.R;

import java.util.ArrayList;


public class MyFragment4 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData2> myDataset;
    private FloatingActionButton mbtn;
    public static Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_4, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.notice_board);
        mbtn = (FloatingActionButton) v.findViewById(R.id.fab);
        mContext = getContext();
        setRecyclerView();
        setBtn();
        return v;
    }


    private void setRecyclerView()
    {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new NoticeBoardRecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData2("아 기분 좋다", "딱좋다" , "2017/07/08"));
        myDataset.add(new MyData2("아 기분 좋다", "딱좋다" , "2017/07/08"));
        myDataset.add(new MyData2("아 기분 좋다", "딱좋다" , "2017/07/08"));
        myDataset.add(new MyData2("아 기분 좋다", "딱좋다" , "2017/07/08"));

    }

    private void setBtn()
    {
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),NoticeBoardActivity.class);
                startActivity(intent);
            }
        });
    }
}
