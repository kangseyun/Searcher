package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.LoginData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by x86kernel on 7/21/17.
 */

public interface Login {
    @FormUrlEncoded
    @POST("/login/")
    Call<List<LoginData>> requestLogin(@Field("userEmail") String email, @Field("userDisplayName") String displayName);

    @GET("/logout/")
    Call<List<LoginData>> requestLogout(@Query("userToken") String token);

    @GET("/token_check")
    Call<List<LoginData>> checkInvalidToken(@Query("userToken") String token);
}
