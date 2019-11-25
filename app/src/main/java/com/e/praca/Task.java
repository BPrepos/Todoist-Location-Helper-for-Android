package com.e.praca;


import java.io.Serializable;

public class Task implements Serializable {
    private long taskId;
    private String name;

    Task(String name, long taskId)
    {
        this.name = name;
        this.taskId = taskId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setTaskId(long id)
    {
        this.taskId = id;
    }

    public long getTaskId() {return taskId;}
}