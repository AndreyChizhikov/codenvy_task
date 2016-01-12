package com.chigikov.controller;

import com.chigikov.dao.TaskDAO;
import com.chigikov.dao.daoImpl.TaskDAOImpl;
import com.chigikov.entity.Task;
import com.chigikov.utilities.JSONCreation;
import com.chigikov.utilities.Validate;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by andrey on 03.01.2016.
 */
public class ResultController extends HttpServlet {

    private final static TaskDAO    taskDAO    = new TaskDAOImpl();
    private final static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Task task = taskDAO.getTaskById(id);
            if (task == null) {
                throw new ServerException("Task not found");
            }
            PrintWriter printWriter = resp.getWriter();
            printWriter.print("Task:" + task.toString());
            printWriter.close();
        } else {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter pw = resp.getWriter();
            pw.print(JSONCreation.taskCollectionToJSON(taskDAO.getAllTasks(), dateFormat));
            pw.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Validate.isValidTask(req.getParameter("id"), req.getParameter("targetDate"), req.getParameter("text"))) {
            Task task = new Task(req.getParameter("id"), req.getParameter("targetDate"), dateFormat,
                                 req.getParameter("text"));
            taskDAO.addTask(req.getParameter("id"), task);
            PrintWriter pw = resp.getWriter();
            pw.print("ID creating task: " + task.getId());
            pw.close();
        } else {
            throw new ServerException("You need fill all fields!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data;
        JSONObject json;
        if ((data = br.readLine()) != null) {
            json = new JSONObject(data);

            br.close();

            if (Validate.isValidTask(json.getString("id"),
                                     json.getString("dateCreation"),
                                     json.getString("targetDate"),
                                     json.getString("text"))) {
                Task task = new Task(json.getString("id"), json.getString("dateCreation"), json.getString("targetDate"),
                                     dateFormat, json.getString("text"));
                taskDAO.updateTask(json.getString("id"), task);
            } else {
                throw new ServletException("You need fill all fields!");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String id;
        if ((id = br.readLine()) != null) {
            Task task = taskDAO.getTaskById(id);
            if (task == null) {
                throw new ServletException("Task not found");
            }
            taskDAO.deleteTask(id);
        }
    }
}

