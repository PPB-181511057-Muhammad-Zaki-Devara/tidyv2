package com.deva.tidyv2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM tasks")
    void deleteAll();

    @Query("SELECT * from tasks ORDER BY due ASC")
    LiveData<List<Task>> getTasks();

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Task task);
}
