package com.monad.searcher.Model;

import android.content.Intent;
import android.util.Log;

import com.monad.searcher.Retrofit.Login;
import com.monad.searcher.Util.RealmManager;
import com.monad.searcher.Util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by x86kernel on 7/21/17.
 */

public class LoginSingleton {
    private static LoginSingleton instance;

    private LoginSingleton() {
        this.status = new String();
    }

    private String status;
    private String email;
    private String token;
    private String display_name;
    private String push;
    private boolean flag;

    public void setStatus(String status) { this.status = status; }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public void setDisplayName(String displayName) { this.display_name = displayName; }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getStatus() {
        while(status.equals("")) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
        }

        return status;
    }

    public String getEmail() {
        return email;
    }
    public String getToken() {
        return token;
    }
    public String getDisplayName() { return display_name; }
    public String getPush() { return push; }
    public boolean getFlag() { return flag; }
    public static LoginSingleton getInstance() {
        if(instance == null)
            instance = new LoginSingleton();
        return instance;
    }

    public void requestLogin(String email) {
        Retrofit retrofit;
        Login login;

        retrofit = RetrofitService.getInstnace();
        login = retrofit.create(Login.class);

        Call<LoginData> load = login.requestLogin(email, display_name);

        load.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                LoginData data = response.body();

                setEmail(data.getEmail());
                setToken(data.getToken());
                setPush(data.getPush());
                Log.i("push", data.getPush());

                RealmManager.open();
                RealmManager.CreateLoginDao().save(data);
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                //Log.d("fail", t.getMessage());
            }
        });
    }

    public void requestLogout() {
        Retrofit retrofit;
        Login logout;

        if(token == null) {
           return;
        }

        retrofit = RetrofitService.getInstnace();
        logout = retrofit.create(Login.class);

        Call<LoginData> load = logout.requestLogout(this.getToken());
        load.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

    public void checkInvalidToken(final Callback<LoginData> callback) {
        Retrofit retrofit;
        Login checkToken;

        retrofit = RetrofitService.getInstnace();
        checkToken = retrofit.create(Login.class);

        setStatus("");
        Call<LoginData> load = checkToken.checkInvalidToken(this.getToken());

        load.enqueue(callback);
    }
}
