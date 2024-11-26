package com.nextuple.tasksql;

import com.nextuple.platform.tasks.pojo.CreateTaskPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskTestController {
    private  final TaskTestService taskTestService;
    private final TaskExceptionHandler exceptionHandler;

    @GetMapping("/processTask")
    public String processTask() {
        try {
            taskTestService.simulateTaskOperation();
            return "Task processed successfully!";
        } catch (Exception e) {
            exceptionHandler.createTaskAndLogError(
                    CreateTaskPojo.builder()
                            .request("sample request")
                            .taskInfo(TaskInformation.TASK_FEED)
                            .tenant("tenant123")
                            .eventType("TASK_ERROR")
                            .stackTrace(e.getStackTrace().toString())
                            .errorMessage(e.getMessage())
                            .build());
            return "An error occurred, task has been logged!";
        }
    }


}
