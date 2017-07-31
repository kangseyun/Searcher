package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.ConditionModel;

import java.util.List;

import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by temp on 2017. 7. 28..
 */

public interface Condition {
    @GET("/condition/gets/")
    Call<List<ConditionModel>> getCondition(@Query("request_email") String request_email);
}
