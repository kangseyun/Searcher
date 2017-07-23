package com.monad.searcher.Model;

import android.content.Intent;
import android.util.Log;

import com.monad.searcher.Retrofit.Login;
import com.monad.searcher.Util.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.monad.searcher.R.id.login;

/**
 * Created by x86kernel on 7/21/17.
 */

public class LoginSingleton {
    private static LoginSingleton instance;

    private LoginSingleton() {
        this.status = new String();
        Log.d("Singleton Test", "init");
    }

    private String status;
    private String email;
    private String token;
    private String display_name;

    public void setStatus(String status) { this.status = status; }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setDisplayName(String displayName) { this.display_name = displayName; }

    public String getStatus() {
        Log.d("status", status);
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

        Call<List<LoginData>> load = login.requestLogin(email, display_name);

        load.enqueue(new Callback<List<LoginData>>() {
            @Override
            public void onResponse(Call<List<LoginData>> call, Response<List<LoginData>> response) {
                List<LoginData> data = response.body();

                setEmail(data.get(0).getEmail());
                setToken(data.get(0).getToken());
            }

            @Override
            public void onFailure(Call<List<LoginData>> call, Throwable t) {
                Log.d("fail", t.getMessage());
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

        Call<List<LoginData>> load = logout.requestLogout(this.getToken());
        load.enqueue(new Callback<List<LoginData>>() {
            @Override
            public void onResponse(Call<List<LoginData>> call, Response<List<LoginData>> response) {
                List<LoginData> data = response.body();
            }

            @Override
            public void onFailure(Call<List<LoginData>> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

    public void checkInvalidToken(final Callback<List<LoginData>> callback) {
        Retrofit retrofit;
        Login checkToken;

        retrofit = RetrofitService.getInstnace();
        checkToken = retrofit.create(Login.class);

        setStatus("");

        Call<List<LoginData>> load = checkToken.checkInvalidToken(this.getToken());

        load.enqueue(callback);
    }
}
