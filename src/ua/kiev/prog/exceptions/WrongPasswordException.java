package ua.kiev.prog.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WrongPasswordException extends RuntimeException{

    public Integer status = 400;
    public String message = "Wrong password.";

    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
        this.message = message;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
