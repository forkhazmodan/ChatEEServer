package ua.kiev.prog.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.models.Message;

//TODO: make error responses doing this stuff inside exceptions
public class DuplicateUserException extends RuntimeException {

    public Integer status = 400;
    public String message = "The user was already registered.";

    public DuplicateUserException(String message) {
        super(message);
        this.message = message;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
