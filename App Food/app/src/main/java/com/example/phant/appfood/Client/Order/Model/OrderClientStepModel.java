package com.example.phant.appfood.Client.Order.Model;

import android.content.Context;

import com.example.phant.appfood.Client.Order.Presenter.OrderClientStepPresenter;
import com.example.phant.appfood.Model.Order;
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
    void config(){
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.firebaseStorage = FirebaseStorage.getInstance("gs://app-food-8f3fb.appspot.com");
        this.storageReference = firebaseStorage.getReference();
    }
    public void sendOrder (Order order){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hoursFormat = new SimpleDateFormat("HH:mm:ss");
        String gio = hoursFormat.format(calendar.getTime());
        String ngay = dateFormat.format(calendar.getTime());
        order.setDate(gio+" "+ngay);
        databaseReference.child("Order").push().setValue(order);
    }
}
