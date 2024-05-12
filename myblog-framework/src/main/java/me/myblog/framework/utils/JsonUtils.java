package me.myblog.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }

    public static String toJson(Object source) {
        return GSON.toJson(source);
    }

    public static <T> T fromJson(String source, Class<T> clazz) {
        return GSON.fromJson(source, clazz);
    }

    public static <T> T toList(String source, Class<T> clazz) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return GSON.fromJson(source, clazz);
    }
}
