package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.CommunityModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by x86kernel on 7/22/17.
*/

public interface Community {
    @GET("/board/")
    Call<List<CommunityModel>> getArticles();

    @FormUrlEncoded
    @POST("/board/post/")
    Call<List<CommunityModel>> postArticles(@Field("subject") String subject, @Field("content") String content, @Field("token") String token);
}
