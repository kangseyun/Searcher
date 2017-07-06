package com.monad.searcher.Model;


public class Fragment2Model {
    private int point;
    private String name;

    public Fragment2Model() {

    }

    public Fragment2Model(int point, String name) {
        this.point = point;
        this.name = name;
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
}
