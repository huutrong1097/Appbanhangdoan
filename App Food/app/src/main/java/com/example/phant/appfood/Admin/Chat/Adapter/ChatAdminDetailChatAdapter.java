package com.example.phant.appfood.Admin.Chat.Adapter;

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

public class ChatAdminDetailChatAdapter extends RecyclerView.Adapter {
    private List<Chat> chatList;
    private static final int send = 0;
    private static final int receiver = 1;
    private User user;

    public ChatAdminDetailChatAdapter(List<Chat> chatList, User user) {
        this.chatList = chatList;
        this.user = user;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getDetailChat().getIdUser().equals(user.getIdUser()))
            return receiver;
        return send;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType == send) {
            AdapterChatSendBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_chat_send, parent, false);
            return new MyViewHolder(binding.getRoot());
        } else {
            AdapterChatReceiverBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_chat_receiver, parent, false);
            return new MyViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        if (holder.getItemViewType()==send){
            AdapterChatSendBinding binding = DataBindingUtil.findBinding(holder.itemView);
            binding.textSend.setText(chat.getDetailChat().getBodyMessages());
        }else {
            AdapterChatReceiverBinding binding = DataBindingUtil.findBinding(holder.itemView);
            binding.textBodyReceiver.setText(chat.getDetailChat().getBodyMessages());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
