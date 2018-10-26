package com.example.phant.appfood.Admin.Chat.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.phant.appfood.Admin.Chat.Adapter.ChatAdminListUserAdapter;
import com.example.phant.appfood.Admin.Chat.Fragment.ChatAdminDetailChatFragment;
import com.example.phant.appfood.Admin.Chat.Presenter.ChatAdminPresenterImp;
import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.DetailChat;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityChatAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatAdminActivity extends AppCompatActivity implements ChatAdminView {
    private ActivityChatAdminBinding binding;
    private List<Chat> listUser;
    private ChatAdminPresenterImp presenterImp;
    private ChatAdminListUserAdapter adapter;
    private Intent intent;
    private User userAdmin;
    private FragmentManager fragmentManager;
    private ChatAdminDetailChatFragment chatFragment;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat_admin);
        bundle = savedInstanceState;
        this.configView();
        this.listenResult();
    }

    void configView(){
        intent = getIntent();
        userAdmin = (User) intent.getSerializableExtra("user");
        fragmentManager = getSupportFragmentManager();
        presenterImp = new ChatAdminPresenterImp(this,this,userAdmin);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        binding.recyclerViewChatAdmin.setLayoutManager(linearLayoutManager);
        listUser = new ArrayList<>();
        adapter = new ChatAdminListUserAdapter(listUser);
        binding.recyclerViewChatAdmin.setAdapter(adapter);
        presenterImp.getListUser();
    }

    void listenResult(){
        adapter.onCallback(new ChatAdminListUserAdapter.CallbackListUserAdapter() {
            @Override
            public void resultUser(final User user) {
                binding.fragmentDetailChatAdmin.setVisibility(View.VISIBLE);
                if (bundle==null){
                    chatFragment = new ChatAdminDetailChatFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentDetailChatAdmin,chatFragment,"detailChat");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else {
                    chatFragment = (ChatAdminDetailChatFragment) fragmentManager.findFragmentByTag("detailChat");
                }
                chatFragment.receiverUser(user);
                presenterImp.getMessage(user);
                chatFragment.onCallback(new ChatAdminDetailChatFragment.CallbackDetailChatFragment() {
                    @Override
                    public void resultMessage(Chat chat) {
                      String bodyMessage = chat.getDetailChat().getBodyMessages();
                      String image = chat.getDetailChat().getImageMessages();
                      chat.setDetailChat(new DetailChat(userAdmin.getIdUser(),bodyMessage,image));
                      presenterImp.sendMessage(chat);
                    }
                });
            }
        });

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
    public void displayListUser(List<Chat> listUser) {
        this.listUser.addAll(listUser);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayFragmentChat(Chat chat) {
        chatFragment.getDetailMessage(chat);
    }

}
