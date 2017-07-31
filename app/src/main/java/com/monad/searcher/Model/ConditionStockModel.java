package com.monad.searcher.Model;

/**
 * Created by temp on 2017. 7. 30..
 */

public class ConditionStockModel {
    private String item_code;
    private String item_name;
    private String item_transactions;

    private float item_current_price;
    private float item_high_price;
    private float item_low_price;
    private float item_price;
    private float item_percentage;

    public ConditionStockModel() {

    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_transactions() {
        return item_transactions;
    }

    public void setItem_transactions(String item_transactions) {
        this.item_transactions = item_transactions;
    }

    public float getItem_current_price() {
        return item_current_price;
    }

    public void setItem_current_price(float item_current_price) {
        this.item_current_price = item_current_price;
    }

    public float getItem_high_price() {
        return item_high_price;
    }

    public void setItem_high_price(float item_high_price) {
        this.item_high_price = item_high_price;
    }

    public float getItem_low_price() {
        return item_low_price;
    }

    public void setItem_low_price(float item_low_price) {
        this.item_low_price = item_low_price;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item__price) {
        this.item_price = item__price;
    }

    public float getItem_percentage() {
        return item_percentage;
    }

    public void setItem_percentage(float item_percentage) {
        this.item_percentage = item_percentage;
    }
}
