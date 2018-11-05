package com.example.phant.appfood.Client.Order.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phant.appfood.Client.Order.Presenter.OrderClientStepPresenter;
import com.example.phant.appfood.Model.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderClientStepModel {
    private Context context;
    private OrderClientStepPresenter presenter;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public OrderClientStepModel(Context context, OrderClientStepPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.config();
    }

    void config() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }

    public void sendOrder(Order order) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hoursFormat = new SimpleDateFormat("HH:mm:ss");
        String gio = hoursFormat.format(calendar.getTime());
        String ngay = dateFormat.format(calendar.getTime());
        order.setDate(gio + " " + ngay);
        databaseReference.child("Order").push().setValue(order);
    }

    public void getOrder(final Order order1) {
        databaseReference.child("Order").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = dataSnapshot.getValue(Order.class);
                order.setKey(dataSnapshot.getKey());
                if (order.getIdCustomer().equals(order1.getIdCustomer())&&order.getDate().equals(order1.getDate())) {
                    presenter.resultDetailOrder(order);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = dataSnapshot.getValue(Order.class);
                order.setKey(dataSnapshot.getKey());
                presenter.resultChangeDetailOrder(order);
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
}
