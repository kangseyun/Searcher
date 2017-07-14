package com.monad.searcher.Retrofit;

import com.monad.searcher.Model.BasicStockModel;
import com.monad.searcher.Model.IssueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by temp on 2017. 7. 14..
 */

public interface IssueRetrofit {
    @GET("/issue/")
    Call<List<IssueModel>> getIssue();
}
