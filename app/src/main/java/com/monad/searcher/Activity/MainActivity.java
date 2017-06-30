package com.monad.searcher.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.monad.searcher.Adapter.FragmentAdapter;
import com.monad.searcher.Adapter.Pager;
import com.monad.searcher.Fragment.MyFragment;
import com.monad.searcher.Fragment.MyFragment2;
import com.monad.searcher.Fragment.MyFragment3;
import com.monad.searcher.Fragment.MyFragment4;
import com.monad.searcher.R;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView title;
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
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
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
        title.setText("홈");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
