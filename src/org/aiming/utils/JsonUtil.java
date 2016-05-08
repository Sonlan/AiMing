package org.aiming.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static String toJson (Object data){
        return gson.toJson(data);
    }

    public static<T> T toObject (String json, Class<T> clazz) throws JsonSyntaxException{
        return gson.fromJson(json,clazz);
    }
}