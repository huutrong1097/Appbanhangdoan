package com.example.phant.appfood.Admin.Chat.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phant.appfood.Admin.Chat.Presenter.ChatAdminPresenter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

    public void getListUser(){

    }
}
