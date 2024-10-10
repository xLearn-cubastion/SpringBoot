package com.shubh.library_management.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shubh.library_management.Entities.Task;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private long idCounter = 0L;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {
        return tasks.stream().filter(task -> task.getId()
                .equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task addTask(Task task){
        task.setId(++idCounter);
        tasks.add(task);
        return task;
    }

    public Task updateTask(Long id, Task updatedTask){
        Optional<Task> taskOptional = tasks.stream().filter(task -> task.getId().equals(id)).findFirst();

        taskOptional.ifPresent(task ->{
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
        });

        return taskOptional.orElse(null);
    }
    public boolean deleteTask(Long id){
        return tasks.removeIf(task-> task.getId().equals(id));
    }
}
