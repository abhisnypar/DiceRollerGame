package com.example.c15333.diceroller.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c15333.diceroller.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by c15333 on 2/14/17.
 * <p>
 * Using the recycler view for populating the list view and Holder design pattern for effecting list view
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {
    private ArrayList<String> list;
    private Context context;
    private LayoutInflater mLayoutInflater = null;

    public RecylerViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * ViewHolder design pattern class. Here we will initialize the views once and reuse them.
     */

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_number_textview)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.scorelistview, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Log.d("Data from list", String.valueOf(list.size()));
        holder.textView.setText(list.get(position));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
