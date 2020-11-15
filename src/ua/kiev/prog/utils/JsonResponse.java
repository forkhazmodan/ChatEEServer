package ua.kiev.prog.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class JsonResponse {

    @Expose()
    Integer status;

    @Expose()
    String message;

    public JsonResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static JsonResponse getInstance(Integer status, String message) {
        return new JsonResponse(status, message);
    }

    public String toJSON() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(this);
    }
}
