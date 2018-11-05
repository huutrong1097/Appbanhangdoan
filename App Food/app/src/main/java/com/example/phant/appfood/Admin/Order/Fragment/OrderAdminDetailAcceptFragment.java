package com.example.phant.appfood.Admin.Order.Fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentOrderAdminAcceptBinding;

@SuppressLint("ValidFragment")
public class OrderAdminDetailAcceptFragment extends Fragment {
    public interface CallbackDetailAcceptFragment {
        void result(Order order);
    }

    private CallbackDetailAcceptFragment callbackDetailAcceptFragment;

    public void onCallback(CallbackDetailAcceptFragment callbackDetailAcceptFragment) {
        this.callbackDetailAcceptFragment = callbackDetailAcceptFragment;
    }

    private FragmentOrderAdminAcceptBinding binding;
    private int type = 0;
    private Order orderd;

    public OrderAdminDetailAcceptFragment(int type, Order order) {
        this.type = type;
        this.orderd = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_admin_accept, container, false);
        this.configView();
        this.listenClick();
        return binding.getRoot();
    }

    void configView() {
        if (type == 0) return;
        else {
            binding.textViewDetail.setText("Đã hủy đơn hàng");
            binding.button.setVisibility(View.INVISIBLE);
        }
    }

    void listenClick() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (callbackDetailAcceptFragment == null) return;
                orderd.setStatus(2);
                callbackDetailAcceptFragment.result(orderd);
            }
        });
    }
}
