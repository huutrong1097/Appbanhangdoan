package com.example.phant.appfood.Admin.Chat.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Admin.Chat.Adapter.ChatAdminDetailChatAdapter;
import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.DetailChat;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentDetailChatAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatAdminDetailChatFragment extends Fragment {
    public interface CallbackDetailChatFragment{
        void resultMessage(Chat chat);
    }
    private CallbackDetailChatFragment callbackDetailChatFragment;
    public void onCallback(CallbackDetailChatFragment callbackDetailChatFragment){
        this.callbackDetailChatFragment = callbackDetailChatFragment;
    }
    private FragmentDetailChatAdminBinding binding;
    private User user;
    private ChatAdminDetailChatAdapter adapter;
    private List<Chat> detailChat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_chat_admin, container, false);
        this.configView();
        this.listenClick();
        return binding.getRoot();
    }

    void configView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        binding.layoutFragmentChat.recyclerViewChatClient.setLayoutManager(linearLayoutManager);
        detailChat = new ArrayList<>();
        adapter = new ChatAdminDetailChatAdapter(detailChat, user);
        binding.layoutFragmentChat.recyclerViewChatClient.setAdapter(adapter);
    }

    void listenClick(){
        binding.layoutFragmentChat.buttonSendMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackDetailChatFragment==null)return;
               String body = binding.layoutFragmentChat.editBodyMessages.getText().toString();
               Chat chat = new Chat(user,new DetailChat(null,body,null));
               callbackDetailChatFragment.resultMessage(chat);
               binding.layoutFragmentChat.editBodyMessages.setText("");
            }
        });
    }

    public void receiverUser(User user) {
        this.user = user;
    }

    public void getDetailMessage(Chat chat){
        detailChat.add(chat);
        adapter.notifyDataSetChanged();
    }


}
