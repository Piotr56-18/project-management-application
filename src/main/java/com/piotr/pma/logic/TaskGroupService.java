package com.piotr.pma.logic;

import com.piotr.pma.TaskConfigurationProperties;
import com.piotr.pma.model.TaskGroup;
import com.piotr.pma.model.TaskGroupRepository;
import com.piotr.pma.model.TaskRepository;
import com.piotr.pma.model.projection.GroupReadModel;
import com.piotr.pma.model.projection.GroupWriteModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope()
public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }
    public GroupReadModel createGroup(GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }
    public List<GroupReadModel>readAll(){
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }
    public void toggleGroup(int groupId){
        if(taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(()->new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(!result.isDone());
    }
}
