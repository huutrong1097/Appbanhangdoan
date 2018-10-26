package com.example.phant.appfood.Model;

public class Chat {
    User user;
   DetailChat detailChat;

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
}
