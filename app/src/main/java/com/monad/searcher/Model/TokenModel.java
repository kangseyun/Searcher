package com.monad.searcher.Model;

import io.realm.RealmObject;

/**
 * Created by temp on 2017. 7. 22..
 */

public class TokenModel extends RealmObject {
    private String token;
    private String email;

    public TokenModel() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
