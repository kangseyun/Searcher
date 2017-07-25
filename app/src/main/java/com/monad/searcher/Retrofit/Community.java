package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.CommunityModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by x86kernel on 7/22/17.
*/

public interface Community {
    @GET("/board/")
    Call<List<CommunityModel>> getArticles();

    @GET("/board/get/")
    Call<CommunityModel> getArticle(@Query("n") Integer n, @Query("token") String token);

    @FormUrlEncoded
    @POST("/board/post/")
    Call<List<CommunityModel>> postArticles(@Field("subject") String subject, @Field("content") String content, @Field("token") String token);

    @GET("/board/delete/")
    Call<CommunityModel> deleteArticle(@Query("n") Integer n, @Query("token") String token);
}
