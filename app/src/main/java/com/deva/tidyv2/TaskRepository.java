package com.deva.tidyv2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;


    TaskRepository(Application application) {
       TidyDB db = TidyDB.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    void insert(Task task) {
        TidyDB.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }
    void update(Task task) {
        TidyDB.databaseWriteExecutor.execute(() -> {
            mTaskDao.update(task);
        });
    }
}
