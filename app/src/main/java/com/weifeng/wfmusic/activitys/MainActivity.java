package com.weifeng.wfmusic.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.weifeng.wfmusic.R;
import com.weifeng.wfmusic.adapters.MainPageAdapter;
import com.weifeng.wfmusic.fragments.HomeFragment;
import com.weifeng.wfmusic.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardView mMainCard;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ViewPager2 mViewPager2;
    private List<Fragment> mFragments = new ArrayList<>();
    private MainPageAdapter mPagerAdapter;

    private ToolUtils mToolUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolUtils = ToolUtils.getInstance();
        mToolUtils.setStatusBarColor(this, Color.TRANSPARENT, getResources().getColor(R.color.colorPrimary), true);
        initView();
    }

    private void initView() {
        mMainCard = findViewById(R.id.maincard);
        mDrawerLayout = findViewById(R.id.maindrawer);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float scale = 1-slideOffset;//1~0
                float leftScale = (float) (1-0.3*scale);
                float rightScale = (float) (0.7f+0.3*scale);//0.7~1
                drawerView.setScaleX(leftScale);//1~0.7
                drawerView.setScaleY(leftScale);//1~0.7
                mMainCard.setScaleX(rightScale);
                mMainCard.setScaleY(rightScale);
                mMainCard.setTranslationX(drawerView.getMeasuredWidth()*slideOffset);//0~width
                mMainCard.setRadius(ToolUtils.dip2px(MainActivity.this, 18*slideOffset));
                mMainCard.setElevation(ToolUtils.dip2px(MainActivity.this, 4*slideOffset));
            }
        });
        mNavigationView = findViewById(R.id.mainnav);
        mNavigationView.setBackgroundColor(Color.TRANSPARENT);
        mViewPager2 = findViewById(R.id.mainpager);
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mPagerAdapter = new MainPageAdapter(this, mFragments);
        mViewPager2.setAdapter(mPagerAdapter);
    }
}