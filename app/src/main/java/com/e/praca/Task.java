package com.e.praca;


import java.io.Serializable;

public class Task implements Serializable {
    private int taskId;
    private String name;

    Task(String name, int taskId)
    {
        this.name = name;
        this.taskId = taskId;

    }
}