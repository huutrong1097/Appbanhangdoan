package com.example.phant.appfood.Client.Chat.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phant.appfood.Client.Chat.Presenter.ChatClientPresenter;
import com.example.phant.appfood.Model.Chat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChatClientModel {
    private Context context;
    private ChatClientPresenter presenter;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public ChatClientModel(Context context, ChatClientPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.config();
    }

    void config() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }

    public void sendMessages(Chat chat) {
        databaseReference.child("Messages").push().setValue(chat);
    }

    public void getDataMessages() {
        databaseReference.child("Messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                chat.setKey(dataSnapshot.getKey());
                presenter.getMessagesSuccess(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                chat.setKey(dataSnapshot.getKey());
                Log.e("key", chat.getKey());
                presenter.chaneMess(chat);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.getMessagesFailure(databaseError.getMessage());
            }
        });
    }
}
