package com.example.phant.appfood.Home;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.phant.appfood.R;
import com.example.phant.appfood.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
       /* binding.viewPager.setAdapter(new ViewPagerAdapterHome(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.getTabAt(0).setText("Menu");
        binding.tabLayout.getTabAt(1).setText("About");
        binding.tabLayout.setTabTextColors(Color.parseColor("#5d5d5d"),Color.parseColor("#ff8400"));
        binding.tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff8400"));
*/



    }
}
