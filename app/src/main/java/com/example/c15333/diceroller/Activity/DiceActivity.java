package com.example.c15333.diceroller.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.c15333.diceroller.R;

import java.util.Random;

/**
 * Created by c15333 on 2/14/17.
 *
 * @DiceActivity : Uses this one as the Activity for result and
 * making the all the logic behind the dices. Such that when any activity calls for this activity,
 * they can get the result back.
 */
public class DiceActivity extends Activity {

    public static final String DICE_RESULT = "DICE_RESULT";
    public static final String DICE_ONE = "DICE_ONE";
    public static final String DICE_TWO = "DICE_TWO";
    private final int delayTime = 15;
    private final Random randomGen = new Random();
    private int diceSum;
    public int roll[] = new int[]{6, 6};
    private boolean paused = false;

    /**
     * @param savedInstanceState == null; When the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        paused = false;
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name));
        try {
            rollDice();
        } catch (Exception e) {
        }
        rollDice();
    }

    public void rollDice() {
        if (paused) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doRoll();
            }
        }).start();
    }

    public void doRoll() { // only does a single roll
        roll[0] = randomGen.nextInt(6);
        roll[1] = randomGen.nextInt(6);
        diceSum = roll[0] + roll[1] + 2; // 2 is added because the values of the rolls start with 0 not 1
        try { // delay to alloy for smooth animation
            Thread.sleep(delayTime);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        String value = String.valueOf(diceSum);
        Intent intent = new Intent();
        intent.putExtra(DICE_RESULT, value);
        intent.putExtra(DICE_ONE, roll[0]);
        intent.putExtra(DICE_TWO, roll[1]);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onResume() {
        super.onResume();
        paused = false;
    }

    public void onPause() {
        super.onPause();
        paused = true;
    }

}
