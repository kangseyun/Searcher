package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.ConditionDetailModel;
import com.monad.searcher.Model.ConditionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by temp on 2017. 7. 28..
 */

public interface ConditionDetail {
    @GET("/condition_item/get/")
    Call<List<ConditionDetailModel>> getConditionList(@Query("n") int no);
}
