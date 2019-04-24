package com.example.script;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class r_welcome extends AppCompatActivity {

//    fragmentAdapter fragmentAdapter;
//    ActionBar actionBar;
//    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_welcome);
//        fragmentAdapter=new fragmentAdapter(getSupportFragmentManager());
//        viewPager=(ViewPager)findViewById(R.id.viewpager);
//        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                actionBar=getSupportActionBar();
//                actionBar.setSelectedNavigationItem(position);
//            }
//        });
//        viewPager.setAdapter(fragmentAdapter);
//        actionBar=getSupportActionBar();
//        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
//        ActionBar.TabListener tabListener=new ActionBar.TabListener() {
//            @Override
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//
//            @Override
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//        };
//        actionBar.addTab(actionBar.newTab().setText("1.about your paper").setTabListener(tabListener));
//        actionBar.addTab(actionBar.newTab().setText("upload file").setTabListener(tabListener));
//        actionBar.addTab(actionBar.newTab().setText("choose supervisor").setTabListener(tabListener));


    }
}
