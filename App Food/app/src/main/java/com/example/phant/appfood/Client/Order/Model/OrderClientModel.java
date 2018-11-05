package com.example.phant.appfood.Client.Order.Model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.phant.appfood.Client.Order.Presenter.OrderClientPresenter;
import com.example.phant.appfood.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class OrderClientModel {
    private Context context;
    private OrderClientPresenter presenter;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public OrderClientModel(Context context, OrderClientPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.config();
    }

    void config() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }

    public void getListOrder(final String customer) {
        final List<Order> list = new ArrayList<>();
        databaseReference.child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Order order = d.getValue(Order.class);
                    String customer1 = d.child("idCustomer").getValue(String.class);
                    if (customer1.equals(customer))
                        list.add(0,order);
                }
                presenter.getListOrderSuccess(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
