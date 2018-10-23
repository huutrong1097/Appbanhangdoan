package com.example.phant.appfood.Admin.Chat.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.AdapterListUserChatAdminBinding;

import java.util.List;

public class ChatAdminListUserAdapter extends RecyclerView.Adapter {
    private List<String> listUser;
    private AdapterListUserChatAdminBinding binding;

    public ChatAdminListUserAdapter(List<String> listUser) {
        this.listUser = listUser;
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_list_user_chat_admin, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String user = listUser.get(position);
        binding.textUser.setText(user);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }
}
