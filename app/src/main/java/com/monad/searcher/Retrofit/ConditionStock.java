package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.ConditionDetailModel;
import com.monad.searcher.Model.ConditionStockModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by temp on 2017. 7. 30..
 */

public interface ConditionStock {
    @GET("/condition_item/get/")
    Call<ConditionStockModel> getConditionList(@Query("code") String code);
}
