package com.e.praca;

public class LabelTask {
    private long labelId, taskId;

    LabelTask(long labelId, long taskId)
    {
        this.labelId = labelId;
        this.taskId = taskId;
    }

    public long getLabelId() {
        return labelId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }
}
