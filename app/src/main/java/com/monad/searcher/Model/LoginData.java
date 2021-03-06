package com.monad.searcher.Model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by x86kernel on 7/21/17.
 */

public class LoginData extends RealmObject {
    @PrimaryKey
    private long id;

    @SerializedName("status")
    private String status;

    @SerializedName("email")
    private String email;

    @SerializedName("token")
    private String token;

    @SerializedName("push")
    private String push;


    public String getLoginStatus() {
        return status;
    }
    public String getEmail() {
        return email;
    }
    public String getToken() {
        return token;
    }
    public String getPush() {return push; }
}
