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
import com.example.phant.appfood.databinding.AdapterListUserChatAdminBinding;

import java.util.List;

public class ChatAdminListUserAdapter extends RecyclerView.Adapter {
    public interface CallbackListUserAdapter{
        void resultUser(User user);
    }

    private CallbackListUserAdapter callbackListUserAdapter;
    public void onCallback(CallbackListUserAdapter callbackListUserAdapter){
        this.callbackListUserAdapter=callbackListUserAdapter;
    }
    private List<Chat> listUser;
    private AdapterListUserChatAdminBinding binding;

    public ChatAdminListUserAdapter(List<Chat> listUser) {
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
        final Chat user = listUser.get(position);
        binding.textUser.setText(user.getUser().getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackListUserAdapter==null)return;
                callbackListUserAdapter.resultUser(new User(user.getUser().getIdUser(),user.getUser().getEmail()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }
}
