package com.monad.searcher.Model;

/**
 * Created by temp on 2017. 7. 13..
 */

public class BasicStockModel {
    private String LastTradePrice;
    private String StockSymbol;

    public String getLastTradePrice() {
        return LastTradePrice;
    }

    public void setLastTradePrice(String lastTradePrice) {
        LastTradePrice = lastTradePrice;
    }

    public String getStockSymbol() {
        return StockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        StockSymbol = stockSymbol;
    }
}
