package com.example.phant.appfood.Client.Chat.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Client.Chat.Adapter.ChatClientAdapter;
import com.example.phant.appfood.Client.Chat.Presenter.ChatClientPresenterImp;
import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.DetailChat;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityChatClientBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatClientActivity extends AppCompatActivity implements ChatClientView {
    private ActivityChatClientBinding binding;
    private Intent intent;
    private ChatClientPresenterImp presenterImp;
    private User user;
    private List<Chat> chatList;
    private ChatClientAdapter adapter;
    List<String> keyChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_client);
        this.configView();
        this.listenClick();
    }

    void configView() {
        keyChat= new ArrayList<>();
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        presenterImp = new ChatClientPresenterImp(this, this,user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       linearLayoutManager.setStackFromEnd(true);
        binding.recyclerViewChatClient.setLayoutManager(linearLayoutManager);
        chatList = new ArrayList<>();
        adapter = new ChatClientAdapter(chatList, user);
        binding.recyclerViewChatClient.setAdapter(adapter);
        presenterImp.getData();
    }

    void listenClick() {
        binding.buttonSendMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.editBodyMessages.getText().toString();
                Chat chat = new Chat(user,new DetailChat(user.getIdUser(),text,null));
                presenterImp.sendMessages(chat);
                binding.editBodyMessages.setText("");
            }
        });
    }

    @Override
    public void showMessages(String messages) {
        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayChat(Chat chat) {
        keyChat.add(chat.getKey());
        chatList.add(chat);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        binding.progressBarChatClient.setVisibility(View.VISIBLE);
        binding.recyclerViewChatClient.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBarChatClient.setVisibility(View.INVISIBLE);
        binding.recyclerViewChatClient.setVisibility(View.VISIBLE);
    }

    @Override
    public void chaneMessa(Chat chat) {
        Log.e("chane", String.valueOf(keyChat.indexOf(chat.getKey())));
        chatList.get(keyChat.indexOf(chat.getKey())).setDetailChat(chat.getDetailChat());
        adapter.notifyItemChanged(keyChat.indexOf(chat.getKey()));
    }
}
