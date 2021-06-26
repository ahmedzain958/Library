package com.zainco.library.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {// the observable object needs to keep references to the observers
    private String news;
    private List<Channel> channels = new ArrayList();

    public void addChannel(Channel channel) {
        channels.add(channel);
    }


    public void removeChannel(Channel channel) {
        channels.remove(channel);

    }

    public void notifyChannels(Object object) {
        news = (String) object;
        for (Channel channel : channels) {
            channel.update(object);
        }
    }
}
