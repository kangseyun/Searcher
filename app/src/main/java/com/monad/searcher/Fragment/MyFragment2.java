package com.monad.searcher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.monad.searcher.Activity.ConditionStockActivity;
import com.monad.searcher.Adapter.Fragment2Adapter;
import com.monad.searcher.Model.Fragment2Model;
import com.monad.searcher.R;

import java.util.ArrayList;

public class MyFragment2 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private Fragment2Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Fragment2Model> myDataset;
    private Spinner spinner;
    private ArrayList<String> test = new ArrayList<>();
    private Fragment2Adapter mmodel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_2, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragment2_recycler);
        setRecyclerView();
        setSpinner();
        return v;
    }

    private void setSpinner() {
        test.add("조건식1");
        test.add("조건식2");
        spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.spin, test);
        spinner.setAdapter(adapter);
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
        mAdapter = new Fragment2Adapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new Fragment2Model(1, "13.78"));
        myDataset.add(new Fragment2Model(1, "3.21"));

        mAdapter.setOnItemClickListener(new Fragment2Adapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Click", position  + "");
                Intent i = new Intent(getContext(), ConditionStockActivity.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    private void setStock()
    {

    }
}
