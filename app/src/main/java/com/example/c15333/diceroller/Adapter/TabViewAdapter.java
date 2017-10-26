package com.example.c15333.diceroller.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.c15333.diceroller.Fragments.DiceGameFragment;
import com.example.c15333.diceroller.Fragments.GameWikiPageFragment;

import java.util.Locale;

/**
 * Created by c15333 on 2/13/17.
 * <p>
 * In this adapter i have initialized the fragments as per their location.
 */

public class TabViewAdapter extends FragmentStatePagerAdapter {
    private static final int DICE = 0;
    private static final int WEB_VIEW = 1;
    Context context;

    public TabViewAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case DICE:
                return DiceGameFragment.newInstance(context);
            case WEB_VIEW:
                return GameWikiPageFragment.newInstance(context);
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unknown position: %d", position));
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case DICE:
                return "DICE GAME";
            case WEB_VIEW:
                return "WEB VIEW";
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unknow position: %d", position));
        }
    }
}
