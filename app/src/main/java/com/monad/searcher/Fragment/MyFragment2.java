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
import com.monad.searcher.Activity.LoginActivity;
import com.monad.searcher.Adapter.Fragment2Adapter;
import com.monad.searcher.Model.BasicStockModel;
import com.monad.searcher.Model.ConditionModel;
import com.monad.searcher.Model.Fragment2Model;
import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.LoginSingleton;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.Condition;
import com.monad.searcher.Util.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyFragment2 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private Fragment2Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Fragment2Model> myDataset;
    private Retrofit retrofit;
    private Condition condition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_2, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragment2_recycler);
        setRecyclerView();
        getData();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getData() {
        retrofit = RetrofitService.getInstnace();
        condition = retrofit.create(Condition.class);
        Call<List<ConditionModel>> load = condition.getCondition("dcp.k953@gmail.com");

        load.enqueue(new Callback<List<ConditionModel>>() {
            @Override
            public void onResponse(Call<List<ConditionModel>> call, Response<List<ConditionModel>> response) {
                List<ConditionModel> size = response.body();
                for (ConditionModel obj : size) {
                    myDataset.add(new Fragment2Model(obj.getExpress_index(), obj.getExpress_name(), obj.getExpress_content()));
                }
                Log.i("result", size.get(0).getExpress_name());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ConditionModel>> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });
    }

    private void setRecyclerView()
    {
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new Fragment2Adapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Fragment2Adapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getContext(), ConditionStockActivity.class);
                i.putExtra("num", myDataset.get(position).getPoint());
                i.putExtra("title", myDataset.get(position).getName());
                i.putExtra("content", myDataset.get(position).getContent());
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }
}
