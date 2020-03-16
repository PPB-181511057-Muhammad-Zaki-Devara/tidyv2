package com.deva.tidyv2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity(tableName = "tasks")
public class Task implements Comparable<Task>, Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="due")
    private String due;

    @ColumnInfo(name="isdone")
    private boolean isDone;

    public Task(String name, String due, boolean isDone, int id){
        this(name, Timestamp.valueOf(due), isDone, id);
    }

    public Task(String name, Timestamp due){
        this(name, due, false);
    }
    public Task(String name, Timestamp due, boolean isDone){
        this(name, due, isDone, 0);
    }
    public Task(String name, Timestamp due, boolean isDone, int id){
        this.name = name;
        this.due = due.toString();
        this.isDone = isDone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getDueTimestamp() {
        return Timestamp.valueOf(due);
    }

    public String getDue() {
        return this.due;
    }


    public boolean isDone(){
        return isDone;
    }
    public void setDone(boolean done){
        this.isDone = done;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public Task getTask(){
        return this;
    }

    @Override
    public int compareTo(Task other) {
        return due.compareTo(other.due);
    }
}
