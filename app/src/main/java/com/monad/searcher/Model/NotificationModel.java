package com.monad.searcher.Model;

import io.realm.RealmObject;

public class NotificationModel extends RealmObject {
    private String content;

    public NotificationModel() {

    }

    public NotificationModel(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
