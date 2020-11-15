package ua.kiev.prog.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NotRegisteredUserException extends RuntimeException {

    public Integer status = 400;
    public String message = "The user not registered.";

    public NotRegisteredUserException() {
    }

    public NotRegisteredUserException(String message) {
        super(message);
        this.message = message;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
