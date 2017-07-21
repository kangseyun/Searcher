package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.LoginData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by x86kernel on 7/21/17.
 */

public interface Login {
    @FormUrlEncoded
    @POST("/login/")
    Call<List<LoginData>> requestLogin(@Field("userEmail") String email);
}
