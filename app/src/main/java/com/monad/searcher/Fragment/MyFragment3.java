package com.monad.searcher.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.monad.searcher.Model.MyData;
import com.monad.searcher.R;

import java.util.ArrayList;

public class MyFragment3 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;
    private LinearLayout mLinearLayout;
    private MyData mMydata;
    private ImageView mImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_3, container, false);
        mLinearLayout = (LinearLayout) v.findViewById(R.id.color);
        mImage = (ImageView) v.findViewById(R.id.image);
        return v;
    }
}