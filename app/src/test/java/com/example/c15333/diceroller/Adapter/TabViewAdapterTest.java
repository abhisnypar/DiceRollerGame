package com.example.c15333.diceroller.Adapter;

import android.support.v4.app.Fragment;

import com.example.c15333.diceroller.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by c15333 on 2/15/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class TabViewAdapterTest {
    private TabViewAdapter tabViewAdapter;

    @Before
    public void setUp() throws Exception {
        tabViewAdapter = new TabViewAdapter(
                null,
                RuntimeEnvironment.application
        );

    }

    @After
    public void tearDown() throws Exception {

        tabViewAdapter = null;
    }

    @Test
    public void getCountOfTabs() {
        assertThat(tabViewAdapter.getCount()).isEqualTo(2);
    }

    @Test
    public void getPageTitleAndFragment_asksItsFragmentAtTheGivenPosition() {
        Fragment fragment1 = tabViewAdapter.getItem(1);
        assertThat(tabViewAdapter.getPageTitle(1)).isEqualTo("WEB VIEW");
        assertThat(fragment1).isNotNull();
    }
}