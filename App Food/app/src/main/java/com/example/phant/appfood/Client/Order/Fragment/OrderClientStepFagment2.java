package com.example.phant.appfood.Client.Order.Fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phant.appfood.Model.Order;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.FragmentStepOrder2Binding;

@SuppressLint("ValidFragment")
public class OrderClientStepFagment2 extends Fragment {
    public interface CallbackStepFagment2 {
        void result(Order order);
    }

    private CallbackStepFagment2 callbackStepFagment2;

    public void onCallback(CallbackStepFagment2 callbackStepFagment2) {
        this.callbackStepFagment2 = callbackStepFagment2;
    }

    private Order order;
    private FragmentStepOrder2Binding binding;

    public OrderClientStepFagment2(Order order) {
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_order2, container, false);
        this.listenClick();
        return binding.getRoot();
    }

    void listenClick() {
        binding.buttonNextStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackStepFagment2 == null) return;
                String name = binding.editTextName.getText().toString();
                String phone = binding.editTextPhone.getText().toString();
                String add = binding.editTextAddress.getText().toString();
                String note = binding.editTextNote.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getActivity().getBaseContext(), "Tên không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phone.isEmpty()) {
                    Toast.makeText(getActivity().getBaseContext(), "Số điện thoại không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (add.isEmpty()) {
                    Toast.makeText(getActivity().getBaseContext(), "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }
                order.setName(name);
                order.setPhone(phone);
                order.setAddress(add);
                order.setNote(note);
                callbackStepFagment2.result(order);
            }
        });
    }


}
