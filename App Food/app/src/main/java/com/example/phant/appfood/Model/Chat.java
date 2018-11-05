package com.example.phant.appfood.Model;

public class Chat {
    User user;
   DetailChat detailChat;
   String key;

    public Chat(User user, DetailChat detailChat) {
        this.user = user;
        this.detailChat = detailChat;
    }

    public Chat() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DetailChat getDetailChat() {
        return detailChat;
    }

    public void setDetailChat(DetailChat detailChat) {
        this.detailChat = detailChat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
