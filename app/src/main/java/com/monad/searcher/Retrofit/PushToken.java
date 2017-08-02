package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.LoginData;
import com.monad.searcher.Model.TokenPushModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by temp on 2017. 7. 27..
 */

public interface PushToken {
    @FormUrlEncoded
    @POST("/get_pushToken/")
    Call<TokenPushModel> pushToken(@Field("pushToken") String token, @Field("userEmail") String email);
}
