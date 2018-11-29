package com.example.phant.appfood.Admin.Order.Fragment;

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

import com.example.phant.appfood.Admin.Order.Adapter.OrderAdminDetailAdapter;
import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentOrderAdminDetailBinding;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class OrderAdminDetailFragment extends Fragment {
    public interface CallbackDetailFragment{
        void chapNhan(Order order);
        void huy(Order order);
    }
    private CallbackDetailFragment callbackDetailFragment;
    public void onCallback(CallbackDetailFragment callbackDetailFragment){
        this.callbackDetailFragment=callbackDetailFragment;
    }
    private FragmentOrderAdminDetailBinding binding;
    private Order order;

    public OrderAdminDetailFragment(Order order) {
        this.order = order;
    }

    private OrderAdminDetailAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_admin_detail, container, false);
        this.configView();
        return binding.getRoot();
    }

    void configView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdminDetailAdapter(order.getFoodList());
        binding.recyclerView.setAdapter(adapter);
        binding.textName.setText(order.getName());
        binding.textPhone.setText(order.getPhone());
        binding.textAddress.setText(order.getAddress());
        binding.textNote.setText(order.getNote());
        ArrayList<String> totalMoneyNew = formatMoney(String.valueOf(order.getTotalMoney()));
        for (String value: totalMoneyNew ) {
            binding.textMoney.append(value);
        }
        binding.buttonChapNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackDetailFragment==null)return;
                order.setStatus(1);
                callbackDetailFragment.chapNhan(order);
            }
        });
        binding.buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackDetailFragment==null)return;
                order.setStatus(-1);
                callbackDetailFragment.huy(order);
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
