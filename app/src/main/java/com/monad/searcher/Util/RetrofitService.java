package com.monad.searcher.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by temp on 2017. 7. 13..
 */

public class  RetrofitService {
    public static String url = "http://192.168.35.201:8000";
    public static Retrofit getInstnace() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
