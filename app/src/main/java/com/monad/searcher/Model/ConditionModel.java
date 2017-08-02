package com.monad.searcher.Model;

/**
 * Created by temp on 2017. 7. 28..
 */

public class ConditionModel {
    private int express_index;
    private String express_name;
    private String express_content;

    public ConditionModel() {

    }


    public int getExpress_index() {
        return express_index;
    }

    public void setExpress_index(int express_index) {
        this.express_index = express_index;
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }

    public String getExpress_content() {
        return express_content;
    }

    public void setExpress_content(String express_content) {
        this.express_content = express_content;
    }
}
