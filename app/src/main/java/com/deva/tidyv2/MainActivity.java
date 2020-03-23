package com.deva.tidyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private TaskViewModel mTaskViewModel;

    private final int ADD_TASK_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final TaskListAdapter mAdapter = new TaskListAdapter(this);
        recyclerView.setAdapter(mAdapter);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                mAdapter.setTasks(tasks);
            }
        });


    }

    public void onClick(View view){
        Intent i = new Intent("com.deva.tidy.AddTaskActivity");
        Bundle b = new Bundle();
        b.putInt("taskNumber", mTaskViewModel.getAllTasks().getValue().size()+1);
        i.putExtras(b);
        startActivityForResult(i, ADD_TASK_REQUEST_CODE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TASK_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Task newTask = (Task) data.getSerializableExtra("newTask");
                mTaskViewModel.insert(newTask);
            }
        }
    }

}
