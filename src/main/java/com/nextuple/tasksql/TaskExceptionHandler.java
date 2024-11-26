package com.nextuple.tasksql;

import com.nextuple.platform.common.base.httpresponse.AppException;
import com.nextuple.platform.tasks.api.TaskApi;
import com.nextuple.platform.tasks.pojo.CreateTaskPojo;
import com.nextuple.platform.tasks.pojo.CreateTaskReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskExceptionHandler {
    private final TaskApi taskApi;
    @Value("${kafka.input.taskTopics}")
    private String kafkaRetryTopic;
    public void createTaskAndLogError(CreateTaskPojo createTaskPojo) {


        Object request = createTaskPojo.getRequest();
        String errorMessage = createTaskPojo.getErrorMessage();
        String tenant = createTaskPojo.getTenant();
        TaskInformation taskInfo = (TaskInformation) createTaskPojo.getTaskInfo();


        CreateTaskReq createTaskReq = getCreateTaskReq(request, errorMessage, tenant, taskInfo);
        taskApi.addTaskForAsync(createTaskReq);
    }

    private CreateTaskReq getCreateTaskReq(
            Object request, String errorMessage, String tenant, TaskInformation taskInfo) {
        return CreateTaskReq.builder()
                .taskType(taskInfo.getTaskType())
                .executionTime(taskInfo.getExecutionTime())
                .methodName(taskInfo.getMethodName())
                .param(request)
                .topicName(kafkaRetryTopic)
                .className(taskInfo.getClassName())
                .lastException(new AppException(errorMessage))
                .tenant(tenant)
                .build();
    }
}
