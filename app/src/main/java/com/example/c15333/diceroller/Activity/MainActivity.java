package com.example.c15333.diceroller.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.c15333.diceroller.Adapter.TabViewAdapter;
import com.example.c15333.diceroller.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main Activity. App starts with this activity.
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final int DICE = 0;
    private static final int WEB_VIEW = 1;

    /**
     * Binding the views.
     */
    @Bind(R.id.tab_Layout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private TabViewAdapter tabViewAdapter;
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);
        ButterKnife.bind(this);
        tabViewAdapter = new TabViewAdapter(getSupportFragmentManager(), this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpViewPager(); //setting up the viewPager.
    }


    private void setUpViewPager() {
        viewPager.setAdapter(tabViewAdapter);
        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(currentTab);
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentTab = viewPager.getCurrentItem();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //noop
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case DICE:
                //TODO for feature implementation
                break;
            case WEB_VIEW:
                //TODO for feature implementation
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //noop
    }
}
