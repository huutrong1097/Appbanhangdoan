package com.example.phant.appfood.Admin.Chat.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phant.appfood.Admin.Chat.Presenter.ChatAdminPresenter;
import com.example.phant.appfood.Model.Chat;
import com.example.phant.appfood.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ChatAdminModel {
    private Context context;
    private ChatAdminPresenter presenter;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public ChatAdminModel(Context context, ChatAdminPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.config();
    }

    void config() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }

    public void getListUser(final User user1) {
        final List<Chat> listUser = new ArrayList<>();
        databaseReference.child("Messages").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean check = false;
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    User user = i.child("user").getValue(User.class);
                    if (user.getIdUser().equals(user1.getIdUser())) {
                        continue;
                    } else {
                        for (Chat chat : listUser) {
                            if (chat.getUser().getIdUser().equals(user.getIdUser())) {
                                check = true;
                                break;
                            } else check = false;
                        }
                        if (!check) {
                            listUser.add(new Chat(user, null));
                        }
                    }

                }
                presenter.getListUserSuccess(listUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.getDataFailure(databaseError.getMessage());
            }
        });
    }

    public void getDataDetailChat(final User user) {
        databaseReference.child("Messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                if (chat.getUser().getIdUser().equals(user.getIdUser())) {
                    presenter.getDetailMessages(chat);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.getDataFailure(databaseError.getMessage());
            }
        });
    }

    public void sendMessages(Chat chat){
        databaseReference.child("Messages").push().setValue(chat);
    }
}
