package com.chigikov.utilities;

import com.chigikov.entity.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.rmi.ServerException;
import java.text.DateFormat;
import java.util.Collection;


/**
 * Created by andrey on 03.01.2016.
 */
public interface JSONCreation {

    static String taskCollectionToJSON(Collection<Task> taskCollection,
                                       DateFormat dateFormat) throws ServerException {
        String JSONString;
        JSONArray JSONArray = new JSONArray();
        try {
            for (Task task : taskCollection) {
                JSONObject JSONTask = new JSONObject();
                JSONTask.put("id", task.getId());
                JSONTask.put("dateCreation", dateFormat.format(task.getDateCreation()));
                JSONTask.put("targetDate", dateFormat.format(task.getTargetDate()));
                JSONTask.put("text", task.getTextTask());
                JSONArray.put(JSONTask);
            }
            JSONString = JSONArray.toString();

        } catch (JSONException e) {
            throw new ServerException("Can not create JSON task list");
        }
        return JSONString;
    }
}
