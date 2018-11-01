package com.example.phant.appfood.Client.Order.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baoyachi.stepview.bean.StepBean;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment1;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment2;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment3;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment4;
import com.example.phant.appfood.Client.Order.Fragment.OrderClientStepFagment5;
import com.example.phant.appfood.Model.Food;
import com.example.phant.appfood.Model.User;
import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityOrderClientStepBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderClientStepActivity extends AppCompatActivity {
    private ActivityOrderClientStepBinding binding;
    private List<StepBean> beanList;
    private Intent intent;
    private List<Food> foodList;
    private User user;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private OrderClientStepFagment1 fagment1;
    private OrderClientStepFagment2 fagment2;
    private OrderClientStepFagment3 fagment3;
    private OrderClientStepFagment4 fagment4;
    private OrderClientStepFagment5 fagment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_client_step);
        this.resultData();
        this.configView();
        this.listenClick();
    }

    void resultData() {
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        foodList = (List<Food>) intent.getSerializableExtra("listCart");
    }

    void configView() {
        this.fragmentManager = getSupportFragmentManager();
        beanList = new ArrayList<>();
        StepBean bean1 = new StepBean("Preview", 0);
        StepBean bean2 = new StepBean("Shipping", -1);
        StepBean bean3 = new StepBean("Confirm", -1);
        StepBean bean4 = new StepBean("Delivering", -1);
        StepBean bean5 = new StepBean("Finish", -1);
        beanList.add(bean1);
        beanList.add(bean2);
        beanList.add(bean3);
        beanList.add(bean4);
        beanList.add(bean5);
        binding.stepView.setStepViewTexts(beanList)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#FF868181"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#FF868181"))
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.colorButton))
                .setStepViewUnComplectedTextColor(Color.parseColor("#FF868181"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));

        fagment1 = new OrderClientStepFagment1(foodList);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.containerStep, fagment1, "fagment1");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    void listenClick() {
        binding.buttonNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
