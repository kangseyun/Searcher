package com.monad.searcher.Model;

import io.realm.RealmObject;

/**
 * Created by temp on 2017. 7. 22..
 */

public class TokenCheckModel extends RealmObject {
    private boolean check;
    public TokenCheckModel() {

    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
