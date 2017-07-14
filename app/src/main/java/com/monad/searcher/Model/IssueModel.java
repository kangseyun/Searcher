package com.monad.searcher.Model;

/**
 * Created by temp on 2017. 7. 14..
 */

public class IssueModel {
    private int id;
    private String subject;
    private String content;

    public IssueModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
