package com.example.phant.appfood.Client.Menu.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phant.appfood.Client.Menu.Presenter.MenuClientPresenter;
import com.example.phant.appfood.Model.Food;
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

public class MenuClientModel {
    private Context context;
    private MenuClientPresenter presenter;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public MenuClientModel(Context context, MenuClientPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.config();
    }

    void config() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }

    public void getDataFood(String typeFood) {
        final List<Food> foods = new ArrayList<>();
        databaseReference.child("food/" + typeFood).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot food : dataSnapshot.getChildren()) {
                    String id = food.getKey();
                    String link = food.child("linkImage").getValue(String.class);
                    String name = food.child("name").getValue(String.class);
                    String price = food.child("unitPrice").getValue(String.class);
                    foods.add(new Food(null,name,price,link));
                }
                presenter.getListFoodSuceess(foods);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.getListFoodFailure(databaseError.getMessage());
            }
        });
    }
}
