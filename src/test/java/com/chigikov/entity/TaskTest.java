package com.chigikov.entity;

import org.junit.Test;


import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrey on 11.01.2016.
 */
public class TaskTest {
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    public void taskConstructorTestWithThreeCorrectParameters() throws Exception {
        Task task = new Task("testId", "12-12-2012", dateFormat, "test text");

        assertEquals(task.getId(), "testId");
        assertEquals(dateFormat.format(task.getTargetDate()),"12-12-2012" );
        assertEquals(task.getTextTask(), "test text");
    }
    @Test (expected = ServerException.class)
    public void taskConstructorTestWithThreeParametersWhereIncorrectDate() throws Exception {
        Task task = new Task("testId", "12.12.2012", dateFormat, "test text");

        assertEquals(task.getId(), "testId");
        assertEquals(dateFormat.format(task.getTargetDate()),"2012-12-12" );
        assertEquals(task.getTextTask(), "test text");
    }
    @Test
    public void taskConstructorTestWithFourCorrectParameters() throws Exception {
        Task task = new Task("testId", "12-12-2012","12-12-2012", dateFormat, "test text");

        assertEquals(task.getId(), "testId");
        assertEquals(dateFormat.format(task.getTargetDate()),"12-12-2012" );
        assertEquals(dateFormat.format(task.getDateCreation()),"12-12-2012" );
        assertEquals(task.getTextTask(), "test text");
    }
    @Test (expected = ServerException.class)
    public void taskConstructorTestWithFourParametersWhereIncorrectDate() throws Exception {
        Task task = new Task("testId", "12.12.2012","12.12.2012", dateFormat, "test text");

        assertEquals(task.getId(), "testId");
        assertEquals(dateFormat.format(task.getTargetDate()),"2012-12-12" );
        assertEquals(dateFormat.format(task.getDateCreation()),"2012-12-12" );
        assertEquals(task.getTextTask(), "test text");
    }
    }
