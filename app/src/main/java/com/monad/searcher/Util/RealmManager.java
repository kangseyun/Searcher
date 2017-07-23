package com.monad.searcher.Util;

import com.monad.searcher.Model.LoginDao;

import io.realm.Realm;

/**
 * Created by x86kernel on 7/23/17.
 */

public class RealmManager {
    private static Realm realm;

    public static Realm open() {
        realm = Realm.getDefaultInstance();
        return realm;
    }

    public static LoginDao CreateLoginDao() {
        checkForOpenRealm();
        return new LoginDao(realm);
    }

    private static void checkForOpenRealm() {
        if (realm == null || realm.isClosed()) {
            throw new IllegalStateException("Except");
        }
    }
}
