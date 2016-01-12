package com.chigikov.utilities;

/**
 * Created by andrey on 04.01.2016.
 */
public interface Validate {

   static boolean isValidTask(String id, String dateCreation, String targetDate, String text){
        return !(id.equals("") || dateCreation.equals("") || targetDate.equals("") || text.equals(""));
    }
    static boolean isValidTask(String id, String targetDate, String text){
        return !(id.equals("") || targetDate.equals("") || text.equals(""));
    }
}
