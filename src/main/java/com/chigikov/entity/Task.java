package com.chigikov.entity;

import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by andrey on 29.12.2015.
 */
public class Task {
    private String     id;
    private String     textTask;
    private Date       dateCreation;
    private Date       targetDate;
    private DateFormat dateFormat;

    public Task(String id,
                String targetDate,
                DateFormat dateFormat,
                String textTask) throws ServerException {
        this.id = id;
        this.dateFormat =dateFormat;
        this.dateCreation = Calendar.getInstance().getTime();
        try {
            this.targetDate = dateFormat.parse(targetDate);
        } catch (ParseException e) {
            throw new ServerException("Can not parse target date. Date format should be: dd-MM-yyyy");
        }
        this.textTask = textTask;
    }

    public Task(String id,
                String dateCreation,
                String targetDate,
                DateFormat dateFormat,
                String textTask) throws ServerException {
        this.id = id;
        this.dateFormat=dateFormat;
        try {
            this.dateCreation = dateFormat.parse(dateCreation);
        } catch (ParseException e) {
            throw new ServerException("Can not parse date creation. Date format should be: dd-MM-yyyy");
        }
        try {
            this.targetDate = dateFormat.parse(targetDate);
        } catch (ParseException e) {
            throw new ServerException("Can not parse target date. Date format should be: dd-MM-yyyy");
        }
        this.textTask = textTask;
    }

    public String getId() {
        return id;
    }

    public Date getTargetDate() {
        return targetDate;
    }


    public Date getDateCreation() {
        return dateCreation;
    }


    public String getTextTask() {
        return textTask;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", textTask='" + textTask + '\'' +
                ", dateCreation=" +dateFormat.format(dateCreation) +
                ", targetDate=" +dateFormat.format(targetDate) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) {
            return false;
        }
        return !(textTask != null ? !textTask
                .equals(task.textTask) : task.textTask != null) && !(targetDate != null ? !targetDate
                .equals(task.targetDate) : task.targetDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (textTask != null ? textTask.hashCode() : 0);
        result = 31 * result + (targetDate != null ? targetDate.hashCode() : 0);
        return result;
    }
}
