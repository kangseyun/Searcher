package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.BasicStockModel;
import com.monad.searcher.Model.RankModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by temp on 2017. 7. 13..
 */

public interface Rank {
    //코스피 상승률
    @GET("/rank/")
    Call<RankModel> getRank();

    //코스피 다운
    @GET("/rank2/")
    Call<RankModel> getRankDown();

    //코스닥 상승률
    @GET("/rank3/")
    Call<RankModel> getKosdaqRank();

    //코스닥 다운률
    @GET("/rank4/")
    Call<RankModel> getKosdaqRankDown();
}
