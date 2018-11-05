package com.example.phant.appfood.Admin.Order.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phant.appfood.Admin.Order.Presenter.OrderAdminPresenter;
import com.example.phant.appfood.Model.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class OrderAdminModel {
    private Context context;
    private OrderAdminPresenter presenter;
    private DatabaseReference databaseReference;

    public OrderAdminModel(Context context, OrderAdminPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void getListOrder() {
        databaseReference.child("Order").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = dataSnapshot.getValue(Order.class);
                order.setKey(dataSnapshot.getKey());
                presenter.resultListOrder(order);
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

            }
        });
    }

    public void upDateOrder(Order order) {
        Map<String, Object> objectMap = order.toMap();
        Map<String, Object> update = new HashMap<>();
        update.put("Order/" + order.getKey(), objectMap);
        databaseReference.updateChildren(update);
    }

}
