package com.chigikov.dao.daoImpl;


import com.chigikov.dao.TaskDAO;
import com.chigikov.entity.Task;

import java.rmi.ServerException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by andrey on 29.12.2015.
 */
public class TaskDAOImpl implements TaskDAO {
    private Map<String, Task> taskMap = new ConcurrentHashMap<>();

    public Task getTaskById(String id) {
        return taskMap.get(id);
    }

    public Collection<Task> getAllTasks(){
        return taskMap.values();
    }

    public void deleteTask(String id) {
        taskMap.remove(id);
    }

    public void updateTask(String id, Task task) throws ServerException {
        if (taskMap.containsKey(id)) {
            taskMap.put(id, task);
        } else {
            throw new ServerException("Task not found");
        }
    }

    public void addTask(String id, Task task) {
        taskMap.put(id, task);
    }
}
