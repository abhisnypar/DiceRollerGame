package com.example.c15333.diceroller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.c15333.diceroller.BuildConfig;
import com.example.c15333.diceroller.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by c15333 on 2/15/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class RecylerViewAdapterTest {

    private RecylerViewAdapter recylerViewAdapter;
    private ArrayList<String> arrayList;

    @Before
    public void setUp() throws Exception {

        arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        recylerViewAdapter = new RecylerViewAdapter(RuntimeEnvironment.application, arrayList);

    }

    @After
    public void tearDown() throws Exception {

        arrayList = null;
        recylerViewAdapter = null;
    }

    @Test
    public void getItemCount_returnsCountFromArrayList() {
        assertThat(recylerViewAdapter.getItemCount()).isEqualTo(5);
    }

    @Test
    public void onCreateViewHolder_createsAViewHolder() {
        final RecyclerView.ViewHolder viewHolder = recylerViewAdapter.onCreateViewHolder(null, 0);
        assertThat(viewHolder.itemView).hasId(R.id.list_item);
    }

    @Test
    public void onBindViewHolder_presentsScoreView(){
        final int position = 1;

        final RecylerViewAdapter.MyViewHolder viewHolder = recylerViewAdapter.onCreateViewHolder(null, position);
        recylerViewAdapter.onBindViewHolder(viewHolder,position);

        final TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.item_number_textview);
        assertThat(textView).isNotEmpty();
    }
}