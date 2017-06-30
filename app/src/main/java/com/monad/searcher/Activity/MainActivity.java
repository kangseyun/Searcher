package com.monad.searcher.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.monad.searcher.Adapter.FragmentAdapter;
import com.monad.searcher.Fragment.MyFragment;
import com.monad.searcher.R;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout();
        setToolbar();

        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager, new MyFragment()).commit();
    }

    private void tabLayout() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyFragment(), "홈");
        adapter.addFragment(new MyFragment(), "홈");
        adapter.addFragment(new MyFragment(), "홈");
        adapter.addFragment(new MyFragment(), "홈");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(this);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("소음 측정");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
