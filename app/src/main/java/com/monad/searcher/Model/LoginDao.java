package com.monad.searcher.Model;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by x86kernel on 7/23/17.
 */

public class LoginDao {
    private Realm realm;

    public LoginDao(@NonNull Realm realm) { this.realm = realm; }

    public void save(final LoginData user) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void remove(@NonNull final RealmObject object) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                object.deleteFromRealm();
            }
        });
    }

    public RealmResults<LoginData> loadAll() {
        return realm.where(LoginData.class).findAll();
    }
}
