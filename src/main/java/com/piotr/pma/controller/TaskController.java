package com.piotr.pma.controller;

import com.piotr.pma.model.Task;
import com.piotr.pma.model.TaskRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks(){
        logger.warn("Exposing all th tasks!");
        return ResponseEntity.ok(taskRepository.findAll());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable pageable){
        logger.warn("Custom pageable!");
        return ResponseEntity.ok(taskRepository.findAll(pageable).getContent());
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/{id}")
    ResponseEntity<?>updateTask(@PathVariable int id,@RequestBody @Valid Task task){
        if(taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        task.setId(id);
        taskRepository.save(task);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET,value = "/tasks/{id}")
    ResponseEntity<Task> readTaskById(@PathVariable int id){
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElse(ResponseEntity.notFound().build());
    }
    @RequestMapping(method = RequestMethod.POST,value = "/tasks")
    ResponseEntity<Task>createTask(@RequestBody @Valid Task task){
        Task result = taskRepository.save(task);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }





}
