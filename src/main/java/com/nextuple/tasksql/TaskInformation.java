package com.nextuple.tasksql;

import java.time.Instant;

public enum TaskInformation {
    TASK_FEED(
            "TASK_PROCESS",
            Instant.now().plusMillis(20000),
            "processTask",
            TaskTestService.class.getName());

    private final String taskType;
    private final Instant executionTime;
    private final String methodName;
    private final String className;

    TaskInformation(String taskType, Instant executionTime, String methodName, String className) {
        this.taskType = taskType;
        this.executionTime = executionTime;
        this.methodName = methodName;
        this.className = className;
    }

    public String getTaskType() {
        return taskType;
    }

    public Instant getExecutionTime() {
        return executionTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }
}
