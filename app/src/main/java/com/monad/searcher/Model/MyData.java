package com.monad.searcher.Model;

public class MyData {
    public String name,KOSPI,KOSDAQ,title,contents;

    public MyData(String name, String KOSPI, String KOSDAQ) {
        this.name = name;
        this.KOSPI = KOSPI;
        this.KOSDAQ = KOSDAQ;
    }

    public MyData(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public int setProperty()
    {
        if(Double.parseDouble(KOSPI)>0)
        {
            return 0;
        }
        else return 1;
    }
}
