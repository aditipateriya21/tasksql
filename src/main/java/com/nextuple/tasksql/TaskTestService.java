package com.nextuple.tasksql;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTestService {

    public void simulateTaskOperation() throws Exception {
        throw new RuntimeException("Something went wrong while processing the task!");
    }



}
