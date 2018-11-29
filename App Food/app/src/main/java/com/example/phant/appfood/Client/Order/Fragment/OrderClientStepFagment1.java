package com.example.phant.appfood.Client.Order.Fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Client.Order.Adapter.OrderClientStepAdapter1;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentStepOrder1Binding;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class OrderClientStepFagment1 extends Fragment {
    public interface CallbackStepFagment1 {
        void result(Order order);
    }

    private CallbackStepFagment1 callbackStepFagment1;

    public void onCallback(CallbackStepFagment1 callbackStepFagment1) {
        this.callbackStepFagment1 = callbackStepFagment1;
    }

    private FragmentStepOrder1Binding binding;
    private OrderClientStepAdapter1 adapter1;
    private List<Food> foodList;
    private int subTotal = 0;
    private int grandTotal = 0;
    private int moneyShip = 3000;

    public OrderClientStepFagment1(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_order1, container, false);
        this.configView();
        this.listenClick();
        return binding.getRoot();
    }

    void configView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewStep1.setLayoutManager(linearLayoutManager);
        adapter1 = new OrderClientStepAdapter1(foodList);
        binding.recyclerViewStep1.setAdapter(adapter1);
        for (Food food : foodList) {
            int i = Integer.parseInt(food.getUnitPrice());
            subTotal = subTotal + i;
        }
        ArrayList<String>  textSubTotal = formatMoney(String.valueOf(subTotal));
        for (String value: textSubTotal){
            binding.textViewSubTotal.append(value);
        }
        ArrayList<String>  textShipping = formatMoney(String.valueOf(moneyShip));
        for (String value: textShipping){
            binding.textViewShipping.append(value);
        }
        grandTotal = subTotal + moneyShip;
        ArrayList<String>  textGrandTotal = formatMoney(String.valueOf(grandTotal));
        for (String value: textGrandTotal){
            binding.textViewGrandTotal.append(value);
        }
    }

    void listenClick() {
        binding.buttonNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackStepFagment1 == null) return;
                Order order = new Order();
                order.setTotalMoney(grandTotal);
                order.setFoodList(foodList);
                callbackStepFagment1.result(order);
            }
        });
    }
    public ArrayList<String> formatMoney (String chuoi){
        ArrayList<String> arrayList = new ArrayList<>();
        int flat = 0;
        for (int i = chuoi.length();i>0;i--){
            if (flat==3){
                arrayList.add(0,".");
                arrayList.add(0, String.valueOf(chuoi.charAt(i-1)));
                flat=1;
            }else {
                arrayList.add(0, String.valueOf(chuoi.charAt(i-1)));
                flat++;
            }
        }
        return arrayList;
    }

}
