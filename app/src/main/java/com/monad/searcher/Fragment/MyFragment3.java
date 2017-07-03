package com.monad.searcher.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monad.searcher.Adapter.RecyclerViewAdapter;
import com.monad.searcher.Model.MyData;
import com.monad.searcher.R;

import java.util.ArrayList;

public class MyFragment3 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_3, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        setRecyclerView();
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
        mAdapter = new RecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData("현대증권", "+19.42%" , "+7.31%"));
        myDataset.add(new MyData("삼성전자", "-13.78%" , "-7.36%"));
        myDataset.add(new MyData("LG화학", "+3.21%" , "+7.49%"));
        myDataset.add(new MyData("현대증권", "+19.42%" , "+7.31%"));
        myDataset.add(new MyData("삼성전자", "-13.78%" , "-7.36%"));
        myDataset.add(new MyData("LG화학", "+3.21%" , "+7.49%"));
        myDataset.add(new MyData("현대증권", "+19.42%" , "+7.31%"));
        myDataset.add(new MyData("삼성전자", "-13.78%" , "-7.36%"));
        myDataset.add(new MyData("LG화학", "+3.21%" , "+7.49%"));
        myDataset.add(new MyData("현대증권", "+19.42%" , "+7.31%"));
        myDataset.add(new MyData("삼성전자", "-13.78%" , "-7.36%"));
        myDataset.add(new MyData("LG화학", "+3.21%" , "+7.49%"));

    }
}
