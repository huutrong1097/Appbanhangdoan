package com.example.phant.appfood.Client.Chat.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterChatReceiverBinding;
import com.example.phant.appfood.databinding.AdapterChatSendBinding;

import java.util.List;

public class ChatClientAdapter extends RecyclerView.Adapter {
    private List<Chat> chatList;
    private static final int SEND = 0;
    private static final int RECEIVER = 1;
    private User user;

    public ChatClientAdapter(List<Chat> chatList, User user) {
        this.chatList = chatList;
        this.user = user;
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getIdUser().equals(user.getIdUser())) return SEND;
        return RECEIVER;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType == RECEIVER) {
            AdapterChatReceiverBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_chat_receiver, parent, false);
            return new MyViewHolder(binding.getRoot());
        } else {
            AdapterChatSendBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_chat_send, parent, false);
            return new MyViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        if (holder.getItemViewType() == SEND) {
            AdapterChatSendBinding binding = DataBindingUtil.findBinding(holder.itemView);
            binding.textSend.setText(chat.getBodyMessages());
        } else {
            AdapterChatReceiverBinding binding = DataBindingUtil.findBinding(holder.itemView);
            binding.textBodyReceiver.setText(chat.getBodyMessages());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
