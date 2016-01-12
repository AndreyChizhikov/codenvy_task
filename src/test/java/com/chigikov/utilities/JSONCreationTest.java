package com.chigikov.utilities;

import com.chigikov.dao.TaskDAO;
import com.chigikov.dao.daoImpl.TaskDAOImpl;
import com.chigikov.entity.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrey on 08.01.2016.
 */

public class JSONCreationTest {
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private TaskDAO dao = new TaskDAOImpl();

    @Before
    public void setUp() throws Exception{
        Task task = new Task("test", "12-12-2012","12-12-2012", dateFormat, "test text");
        dao.addTask(task.getId(), task);

    }
    @After
    public void closeTest(){
        for (Task task:dao.getAllTasks()){
            dao.deleteTask(task.getId());
        }
    }
    @Test
    public void taskCollectionToJSONTest () throws Exception{
        assertEquals(JSONCreation.taskCollectionToJSON(dao.getAllTasks(),dateFormat),"[{\"dateCreation\":\"12-12-2012\",\"targetDate\":\"12-12-2012\",\"id\":\"test\",\"text\":\"test text\"}]");
    }
}
