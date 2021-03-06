package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.ConditionDetailModel;
import com.monad.searcher.Model.ConditionModel;
import com.monad.searcher.Model.ConditionStockModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by temp on 2017. 7. 28..
 */

public interface ConditionDetail {
    @GET("/condition_item/gets/")
    Call<List<ConditionDetailModel>> getStock(@Query("n") int no);
}
