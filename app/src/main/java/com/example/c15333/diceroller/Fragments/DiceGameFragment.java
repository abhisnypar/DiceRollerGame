package com.example.c15333.diceroller.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c15333.diceroller.Activity.DiceActivity;
import com.example.c15333.diceroller.Adapter.RecylerViewAdapter;
import com.example.c15333.diceroller.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Use the {@link DiceGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceGameFragment extends Fragment {

    private static final String SAVED_LIST = "SAVE_LIST_ITEMS";
    /**
     * Injecting the views with the butterKnife.
     *
     * @Bind annotations binds the desired viewGroup classes.
     */
    @Bind(R.id.score_text)
    TextView scoreTextView;
    @Bind(R.id.imageView1)
    ImageView diceOneImage;
    @Bind(R.id.imageView2)
    ImageView diceTwoImage;
    @Bind(R.id.list_view)
    RecyclerView recyclerView;

    public String score;
    public int dice_one;
    public int dice_two;

    private final int[] diceImages = new int[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};
    public static final int ACTION_CODE = 1;
    private Handler animationHandler;
    private ArrayList<String> scoreList;
    private RecylerViewAdapter recylerViewAdapter;
    private ArrayList<String> newArrayList;
    private List<String> stringArrayList;


    public DiceGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param context
     * @return A new instance of fragment DiceGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiceGameFragment newInstance(Context context) {
        DiceGameFragment fragment = new DiceGameFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dice_game, container, false);

        ButterKnife.bind(this, view);//register the butterKnife. If not null exception returns for the views.
        animationHandler = new Handler();

        scoreList = new ArrayList<>();
        newArrayList = new ArrayList<>();
        recylerViewAdapter = new RecylerViewAdapter(getActivity(), scoreList);
        recyclerView.setAdapter(recylerViewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @OnClick(R.id.rollbutton)
    public void onDiceRolled() {
        //Loads the animation to the images and starting the DiceActivity to get the result back.
        //Used for showcasing the startActivityForResult.
        Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.animator.rotator);

        //TODO implementing the animation for the image View.
        // Start animating the images.

        diceOneImage.startAnimation(rotate);
        diceTwoImage.startAnimation(rotate);
        Intent intent = new Intent(getActivity(), DiceActivity.class);
        startActivityForResult(intent, ACTION_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Checks for the result. If it is success we receives the RESULT_OK.
        if (resultCode == Activity.RESULT_OK) {
            setAnimation(data);

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (newArrayList == null) {
            return;
        }
        outState.putStringArrayList(SAVED_LIST, newArrayList);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState == null) {
            return;
        }

        newArrayList = savedInstanceState.getStringArrayList(SAVED_LIST);
    }
/*
    Here we stop the animations and set the values to the adapter and populate them in recycler list view.
     */

    public void setAnimation(Intent data) {

        score = (data.getStringExtra(DiceActivity.DICE_RESULT));
        dice_one = data.getIntExtra(DiceActivity.DICE_ONE, 1);
        dice_two = (data.getIntExtra(DiceActivity.DICE_TWO, 2));

        Resources resources = getResources();

        final Drawable dice[] = new Drawable[6];
        for (int i = 0; i < 6; i++) {
            dice[i] = resources.getDrawable(diceImages[i], getActivity().getTheme());
        }

        animationHandler.sendEmptyMessage(0);

        animationHandler = new Handler() {
            public void handleMessage(Message msg) {

                //Logic: add two Array lists to make the other Array List just as a navigator for the first one as per the requirement.

                scoreList.add(score);
                newArrayList.add(score);
                if (newArrayList.size() > 4) {
                    stringArrayList = newArrayList.subList((newArrayList.size() - 5), newArrayList.size());
                    newArrayList = new ArrayList<>(stringArrayList);
                    scoreList.clear();
                    scoreList.addAll(newArrayList);
                }
                recylerViewAdapter.notifyDataSetChanged();
                diceOneImage.setImageDrawable(dice[dice_one]);
                diceTwoImage.setImageDrawable(dice[dice_two]);
            }
        };

        //Stop the animations.
        diceOneImage.setAnimation(null);
        diceTwoImage.setAnimation(null);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);//UnBind the views.
    }

}
