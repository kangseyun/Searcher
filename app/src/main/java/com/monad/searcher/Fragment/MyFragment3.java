package com.monad.searcher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.monad.searcher.Activity.Fragment3DetailActivity;
import com.monad.searcher.Model.MyData;
import com.monad.searcher.R;

import java.util.ArrayList;

public class MyFragment3 extends Fragment implements View.OnClickListener {
    private View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;
    private LinearLayout mLinearLayout;
    private MyData mMydata;
    private ImageView mImage;
    private Button item1, item2, item3, item4, item5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_3, container, false);
        mLinearLayout = (LinearLayout) v.findViewById(R.id.color);
        mImage = (ImageView) v.findViewById(R.id.image);
        setLayout();

        return v;
    }


    private void setLayout() {
        item1 = (Button) v.findViewById(R.id.fragment3_item1);
        item2 = (Button) v.findViewById(R.id.fragment3_item2);
        item3 = (Button) v.findViewById(R.id.fragment3_item3);
        item4 = (Button) v.findViewById(R.id.fragment3_item4);
        item5 = (Button) v.findViewById(R.id.fragment3_item5);

        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fragment3_item1:
                Intent i1 = new Intent(getContext(), Fragment3DetailActivity.class);
                startActivity(i1);
                break;
            case R.id.fragment3_item2:
                Intent i2 = new Intent(getContext(), Fragment3DetailActivity.class);
                startActivity(i2);
                break;
            case R.id.fragment3_item3:
                Intent i3 = new Intent(getContext(), Fragment3DetailActivity.class);
                startActivity(i3);
                break;
            case R.id.fragment3_item4:
                Intent i4 = new Intent(getContext(), Fragment3DetailActivity.class);
                startActivity(i4);
                break;
            case R.id.fragment3_item5:
                Intent i5 = new Intent(getContext(), Fragment3DetailActivity.class);
                startActivity(i5);
                break;
        }
    }
}