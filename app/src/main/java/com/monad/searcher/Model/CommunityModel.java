package com.monad.searcher.Model;

import java.util.Date;

/**
 * Created by x86kernel on 7/22/17.
 */

public class CommunityModel {
    private int id;

    private String subject;
    private String content;
    private String user_name;

    Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() { return subject; }
    public String getContent() { return content; }
    public String getUserName() { return user_name; };

    public Date getCreated() { return created; }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
