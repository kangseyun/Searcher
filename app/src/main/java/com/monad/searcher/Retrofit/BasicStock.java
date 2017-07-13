package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.BasicStockModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by temp on 2017. 7. 13..
 */

public interface BasicStock {
    @GET("/kospi/")
    Call<List<BasicStockModel>> getKospi();

}
