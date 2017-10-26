package com.example.c15333.diceroller.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.c15333.diceroller.Activity.DiceActivity;
import com.example.c15333.diceroller.BuildConfig;
import com.example.c15333.diceroller.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by c15333 on 2/15/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class DiceGameFragmentTest {

    private DiceGameFragment diceGameFragment;
    private Intent resultDataIntent;

    @Before
    public void setUp() throws Exception {

        diceGameFragment = new DiceGameFragment().newInstance(RuntimeEnvironment.application);
        SupportFragmentTestUtil.startFragment(diceGameFragment);
        resultDataIntent = new Intent();
        resultDataIntent.putExtra(DiceActivity.DICE_ONE, 1);
        resultDataIntent.putExtra(DiceActivity.DICE_TWO, 2);
        resultDataIntent.putExtra(DiceActivity.DICE_RESULT, "3");
    }

    @After
    public void tearDown() throws Exception {

        diceGameFragment = null;
        resultDataIntent = null;
    }

    @Test
    public void onRollButtonClicked() {
        //Not able to test the Animation
        //TODO come up with the best possible solution to test Animation

        ShadowApplication.getInstance().clearStartedServices();
        View view = diceGameFragment.getView().findViewById(R.id.rollbutton);
        assert view != null;
//        view.performClick();
        final Intent intent = new Intent(RuntimeEnvironment.application, DiceActivity.class);
        final Intent expectedIntent = Shadows.shadowOf(diceGameFragment.getActivity()).peekNextStartedActivity();

//        assertThat(expectedIntent).isEqualTo(intent);

    }

    @Test
    public void onActivityResult_receivesScore() {
        ShadowApplication.getInstance().clearStartedServices();
        diceGameFragment.onActivityResult(diceGameFragment.ACTION_CODE, Activity.RESULT_OK, resultDataIntent);
        assertEquals(diceGameFragment.score, "3");
    }
}