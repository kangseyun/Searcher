package com.monad.searcher.Model;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by seyun on 2017. 9. 5..
 */

public class PushModel extends RealmObject {
    private String push;


    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }
}
