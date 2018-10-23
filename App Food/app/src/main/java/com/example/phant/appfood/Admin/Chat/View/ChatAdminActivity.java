package com.example.phant.appfood.Admin.Chat.View;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Admin.Chat.Adapter.ChatAdminListUserAdapter;
import com.example.phant.appfood.Admin.Chat.Presenter.ChatAdminPresenterImp;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityChatAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatAdminActivity extends AppCompatActivity implements ChatAdminView {
    private ActivityChatAdminBinding binding;
    private List<String> listUser;
    private ChatAdminPresenterImp presenterImp;
    private ChatAdminListUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat_admin);
        this.configView();
    }

    void configView(){
        presenterImp = new ChatAdminPresenterImp(this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        binding.recyclerViewChatAdmin.setLayoutManager(linearLayoutManager);
        listUser = new ArrayList<>();
        adapter = new ChatAdminListUserAdapter(listUser);
        binding.recyclerViewChatAdmin.setAdapter(adapter);
        presenterImp.getListUser();
    }

    @Override
    public void showLoading() {
        binding.progressBarChatAdmin.setVisibility(View.VISIBLE);
        binding.recyclerViewChatAdmin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBarChatAdmin.setVisibility(View.INVISIBLE);
        binding.recyclerViewChatAdmin.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessages(String messages) {
        Toast.makeText(this,messages,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayListUser(String user) {
        listUser.add(user);
        adapter.notifyDataSetChanged();
    }
}
