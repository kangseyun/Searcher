package com.monad.searcher.Model;

public class StockData {
    private String stock_name;
    private String code;


    public StockData() {
    }

    public StockData(String stock_name, String code) {
        this.stock_name = stock_name;
        this.code = code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
