package com.zainco.library.designpatterns.behavioral;

public class NewsChannel implements Channel {
    private String news;
    @Override
    public void update(Object news) {
        this.news = (String) news;
    }
}
