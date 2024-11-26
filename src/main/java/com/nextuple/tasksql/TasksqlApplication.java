package com.nextuple.tasksql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableCaching
@ComponentScan({"com.nextuple.tasksql", "com.nextuple.platform"})
@EntityScan({"com.nextuple.tasksql", "com.nextuple.platform"})
@EnableJpaRepositories({"com.nextuple.tasksql", "com.nextuple.platform"})
@EnableScheduling
public class TasksqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksqlApplication.class, args);
	}

}
