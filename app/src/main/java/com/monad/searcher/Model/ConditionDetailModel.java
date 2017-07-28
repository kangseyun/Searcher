package com.monad.searcher.Model;

/**
 * Created by temp on 2017. 7. 28..
 */

public class ConditionDetailModel {
    private int id;
    private String subject;
    private float index;

    public ConditionDetailModel() {

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

    public float getPoint() {
        return index;
    }

    public void setPoint(float index) {
        this.index = index;
    }
}
