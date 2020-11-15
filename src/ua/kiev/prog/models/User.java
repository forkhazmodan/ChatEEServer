package ua.kiev.prog.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class User {

    @Expose()
    private String room;

    @Expose()
    public String login;

    @Expose()
    public boolean isOnline = false;

    @Expose(serialize = false)
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    public String toJSON() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(this);
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.fromJson(s, User.class);
    }

    @Override
    public String toString() {
        return "User{" +
                "room='" + room + '\'' +
                ", login='" + login + '\'' +
                ", isOnline=" + isOnline +
                ", password='" + password + '\'' +
                '}';
    }
}
