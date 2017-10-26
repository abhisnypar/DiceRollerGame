package com.example.c15333.diceroller.Activity;

import com.example.c15333.diceroller.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by c15333 on 2/15/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private ActivityController<MainActivity> mainActivityActivityController;

    @Before
    public void setUp() {
        this.mainActivityActivityController = Robolectric.buildActivity(MainActivity.class).create().start().resume();

    }

    @After
    public void teatDown() {
        this.mainActivityActivityController.pause().stop().destroy();
    }

    @Test
    public void testSetUpViewPagerOnStart() {
        //Making sure the views are initialized.
        assertNotNull(mainActivityActivityController.get().tabLayout);
        assertNotNull(mainActivityActivityController.get().viewPager);
    }
}