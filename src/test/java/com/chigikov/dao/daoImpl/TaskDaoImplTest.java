package com.chigikov.dao.daoImpl;

import com.chigikov.dao.TaskDAO;
import com.chigikov.entity.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrey on 09.01.2016.
 */
public class TaskDaoImplTest {
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private Task task;
    private TaskDAO dao = new TaskDAOImpl();

    @Before
    public void setUp() throws Exception {
        task = new Task("test", "12-12-2012", dateFormat, "test text");
        dao.addTask(task.getId(), task);
    }

    @After
    public void closeTest() {
        dao.getAllTasks().clear();
    }

    @Test
    public void getTaskByIdTest() {
        assertEquals(dao.getTaskById("test"), task);
    }

    @Test
    public void getAllTasksTest() {
        Collection<Task> allTasks = dao.getAllTasks();

        assertEquals(allTasks.size(), 1);
    }

    @Test
    public void taskShouldBeAdded() throws Exception {
        Task task = new Task("test", "12-12-2015", dateFormat, "text task");
        dao.deleteTask("test");

        dao.addTask(task.getId(), task);

        assertEquals(dao.getTaskById("test"), task);
    }

    @Test
    public void deleteTaskTest() {
        dao.deleteTask("test");

        assertEquals(dao.getTaskById("test"), null);
    }

    @Test
    public void updateTaskTestWhenIdExist() throws Exception {
        Task task = new Task("test", "12-12-2015", dateFormat, "update text");

        dao.updateTask(task.getId(), task);

        assertEquals(dao.getTaskById("test"), task);
    }

    @Test(expected = ServerException.class)
    public void updateTaskTestWhenIdFalse() throws Exception {
        Task task = new Task("idNotCorrect", "12-12-2015", dateFormat, "update text");

        dao.updateTask(task.getId(), task);
    }
}
