package com.chigikov.dao;

import com.chigikov.entity.Task;

import java.rmi.ServerException;
import java.util.Collection;

/**
 * Created by andrey on 29.12.2015.
 */
public interface TaskDAO {

    Task getTaskById(String id);

    Collection<Task> getAllTasks();

    void deleteTask(String id);

    void updateTask(String id, Task task) throws ServerException;

    void addTask(String id, Task task);
}
