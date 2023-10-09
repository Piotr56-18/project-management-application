package com.piotr.pma.logic;

import com.piotr.pma.TaskConfigurationProperties;
import com.piotr.pma.model.ProjectRepository;
import com.piotr.pma.model.TaskGroupRepository;
import com.piotr.pma.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService projectService(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskGroupService service,
            final TaskConfigurationProperties config){
        return new ProjectService(repository, taskGroupRepository,service, config);
    }
    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository taskGroupRepository,
            final TaskRepository taskRepository
            ){
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }
}
