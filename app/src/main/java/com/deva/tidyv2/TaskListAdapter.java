package com.deva.tidyv2;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder> {
    //Menyimpan cached tasks
    private List<Task> mTasks;
    private final LayoutInflater mInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout constraintLayout;
        public MyViewHolder(ConstraintLayout v){
            super(v);
            constraintLayout = v;
        }
    }

    public TaskListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ConstraintLayout v = (ConstraintLayout) mInflater.inflate(R.layout.task_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        CheckBox task = (CheckBox)  holder.constraintLayout.getViewById(R.id.taskCheckBox);
        TextView dueDate = (TextView) holder.constraintLayout.getViewById(R.id.due_date);
        TextView dueTime = (TextView) holder.constraintLayout.getViewById(R.id.due_time);

        if (mTasks != null) {
            Task current = mTasks.get(position);
            task.setText(current.getName());
            dueDate.setText(new SimpleDateFormat("dd MMM yyyy").format(current.getDueTimestamp()));
            dueTime.setText(new SimpleDateFormat("hh:mm").format(current.getDueTimestamp()));
            task.setChecked(current.isDone() ? true: false);
        } else {
            task.setText("No Task");
        }

        final int pos = position;
        task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTasks.get(pos).setDone(isChecked);
            }
        });
    }

    public void setTasks(List<Task> tasks){
        Collections.sort(tasks);
        mTasks = tasks;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

}
