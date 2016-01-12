package com.chigikov.controller;

import com.chigikov.entity.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.Collection;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultControllerTest {

    @Mock
    private HttpServletRequest  request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest  requestDoPost;
    @Mock
    private HttpServletResponse responseDoPost;
    @Mock
    private PrintWriter         writer;
    @Mock
    private PrintWriter         writerDoPost;
    @Mock
    private ServletInputStream  servletInputStream;
    @Mock
    private Collection<Task>    taskList;
    @Mock
    private Task                task;

    private ResultController controller = new ResultController();

    @Before
    public void setUp() throws Exception {
        when(requestDoPost.getParameter("id")).thenReturn("test");
        when(requestDoPost.getParameter("targetDate")).thenReturn("12-12-1212");
        when(requestDoPost.getParameter("text")).thenReturn("test Task");
        when(responseDoPost.getWriter()).thenReturn(writerDoPost);
    }

    @Test
    public void getMethodShouldBePerformedWhenIdNotNull() throws Exception {
        controller.doPost(requestDoPost, responseDoPost);

        when(request.getParameter("id")).thenReturn("test");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(response).getWriter();
        verify(writer).print(anyString());
        verify(writer).close();
    }

    @Test
    public void getMethodShouldPerformedWhenIdNull() throws Exception {
        controller.doPost(requestDoPost, responseDoPost);

        when(request.getParameter("id")).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);

        verify(response).getWriter();
        verify(writer).print(anyString());
        verify(writer).close();
    }

    @Test(expected = ServerException.class)
    public void getMethodShouldPerformedWhenTaskNull() throws Exception {
        controller.doPost(requestDoPost, responseDoPost);

        when(request.getParameter("id")).thenReturn("notTest");
        when(response.getWriter()).thenReturn(writer);

        controller.doGet(request, response);
    }

    @Test
    public void postMethodShouldPerformedWhenTaskIsValid() throws Exception {
        controller.doPost(requestDoPost, responseDoPost);

        verify(responseDoPost).getWriter();
        verify(writerDoPost).print(anyString());
        verify(writerDoPost).close();
    }

    @Test(expected = ServerException.class)
    public void postMethodShouldPerformedWhenTaskIsInvalid() throws Exception {
        when(requestDoPost.getParameter("id")).thenReturn("");

        controller.doPost(requestDoPost, responseDoPost);
    }


}