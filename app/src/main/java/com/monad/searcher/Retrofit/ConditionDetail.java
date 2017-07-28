package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.ConditionDetailModel;
import com.monad.searcher.Model.ConditionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by temp on 2017. 7. 28..
 */

public interface ConditionDetail {
    @GET("/Condition/{no}/")
    Call<List<ConditionDetailModel>> getConditionList(@Path("no") String no);
}
