package com.monad.searcher.Model;

import java.util.List;

/**
 * Created by temp on 2017. 8. 2..
 */

public class RankModel {
    private List<String> name;
    private List<String> name2;

    public List<String> getName2() {
        return name2;
    }

    public void setName2(List<String> name2) {
        this.name2 = name2;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
