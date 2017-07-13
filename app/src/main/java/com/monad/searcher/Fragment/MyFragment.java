package com.monad.searcher.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monad.searcher.Model.BasicStockModel;
import com.monad.searcher.R;
import com.monad.searcher.Retrofit.BasicStock;
import com.monad.searcher.Util.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyFragment extends Fragment {
    private View v;
    private TextView kospi, kosdaq, dji, nasdaq;
    private Retrofit retrofit;
    private BasicStock basicStock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_1, container, false);
        setLayout();

        retrofit = RetrofitService.getInstnace();
        basicStock = retrofit.create(BasicStock.class);

        Call<List<BasicStockModel>> load = basicStock.getKospi();

        load.enqueue(new Callback<List<BasicStockModel>>() {
            @Override
            public void onResponse(Call<List<BasicStockModel>> call, Response<List<BasicStockModel>> response) {
                List<BasicStockModel> data = response.body();
                Log.i("data", data.get(0).getLastTradePrice());
            }

            @Override
            public void onFailure(Call<List<BasicStockModel>> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });
        return v;
    }


    private void setLayout() {
        kospi = (TextView) v.findViewById(R.id.kospi_point);
        kosdaq = (TextView) v.findViewById(R.id.kosdaq_point);
        dji = (TextView) v.findViewById(R.id.dji_point);
        nasdaq = (TextView) v.findViewById(R.id.nasdqa_point);
    }
}
