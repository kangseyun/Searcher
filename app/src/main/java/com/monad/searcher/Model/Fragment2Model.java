package com.monad.searcher.Model;


public class Fragment2Model {
    private int point;
    private String name;
    private String content;

    public Fragment2Model() {

    }

    public Fragment2Model(int point, String name, String content) {
        this.point = point;
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
