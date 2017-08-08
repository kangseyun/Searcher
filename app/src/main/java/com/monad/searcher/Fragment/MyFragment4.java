package com.monad.searcher.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monad.searcher.Activity.LoginActivity;
import com.monad.searcher.Activity.NoticeBoardActivity;
import com.monad.searcher.Activity.NoticeViewActivity;
import com.monad.searcher.Adapter.NoticeBoardRecyclerViewAdapter;
import com.monad.searcher.Model.CommunityModel;
import com.monad.searcher.Model.IssueModel;
import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.LoginSingleton;
import com.monad.searcher.Model.MyData2;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.Community;
import com.monad.searcher.Util.RetrofitService;

import org.json.JSONArray;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyFragment4 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private NoticeBoardRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData2> myDataset;
    private FloatingActionButton mbtn;

    public static Context mContext;
    private LoginSingleton login;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_4, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.notice_board);
        mbtn = (FloatingActionButton) v.findViewById(R.id.fab);
        mContext = getContext();
        login = LoginSingleton.getInstance();

        setRecyclerView();
        setBtn();

        return v;
    }

    @Override
    public void onResume() {
        getArticles(mAdapter);
        super.onResume();
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

        mAdapter.setOnItemClickListener(new NoticeBoardRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                LoginSingleton login = LoginSingleton.getInstance();

                Callback<LoginData> callback = new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        LoginData data = response.body();

                        String status = data.getLoginStatus();
                        if(status.equals("valid_token")) {
                            Intent i = new Intent(getContext(), NoticeViewActivity.class);
                            i.putExtra("n", position);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.d("fail", t.getMessage());
                    }
                };

                login.checkInvalidToken(callback);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
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

    private void getArticles(final NoticeBoardRecyclerViewAdapter mAdapter) {
        Retrofit retrofit;
        Community community;

        retrofit = RetrofitService.getInstnace();
        community = retrofit.create(Community.class);



        Call<List<CommunityModel>> load = community.getArticles();
        load.enqueue(new Callback<List<CommunityModel>>() {
            @Override
            public void onResponse(Call<List<CommunityModel>> call, Response<List<CommunityModel>> response) {
                List<CommunityModel> data = response.body();

                myDataset.clear();

                for(CommunityModel i : data) {
                    if (i.getToken().equals(login.getToken())) {
                        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                        myDataset.add(new MyData2(i.getSubject(), i.getUserName(), dateformat.format(i.getCreated())));
                    }
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CommunityModel>> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }
}
