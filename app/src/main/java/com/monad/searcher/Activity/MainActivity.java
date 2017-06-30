package com.monad.searcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.monad.searcher.Adapter.PagerAdapter;
import com.monad.searcher.R;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;
    private ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        setToolbar();
        tabBind();
        setViewPager();
    }

    void tabBind() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabs();
        tabLayout.setOnTabSelectedListener(this);
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(adapter);
    }

    private void setupTabs() {
        // Title Setup
        tabLayout.addTab(tabLayout.newTab().setText("소식"));
        tabLayout.addTab(tabLayout.newTab().setText("추천"));
        tabLayout.addTab(tabLayout.newTab().setText("정보"));
        tabLayout.addTab(tabLayout.newTab().setText("포트폴리오"));
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.toolbar_title);
        setting = (ImageView) findViewById(R.id.setting);
        title.setText("홈");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
