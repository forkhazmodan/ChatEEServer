package ua.kiev.prog.lists;

import ua.kiev.prog.exceptions.DuplicateUserException;
import ua.kiev.prog.exceptions.NotRegisteredUserException;
import ua.kiev.prog.exceptions.WrongPasswordException;
import ua.kiev.prog.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserList {

    private static Map<String, User> registeredUserList = new HashMap<>();
    private static Map<String, User> onlineUserList = new HashMap<>();

    /*
    |-----------------------------------------------------------------------------------------
    | METHODS
    |-----------------------------------------------------------------------------------------
    */
    public static void register(User user) throws DuplicateUserException {

        String name = user.login;

        if (getRegisteredUserList().containsKey(name)) {
            throw new DuplicateUserException(String.format("User with such name:%s already registered.", name));
        }

        getRegisteredUserList().put(name, user);
    }

    public static boolean isRegistered(User user) {
        return getRegisteredUserList().containsKey(user.login);
    }

    public static User login(User user) throws NotRegisteredUserException, WrongPasswordException {

        String name = user.login;

        User searchedUser = getRegisteredUserList().get(user.login);

        if(searchedUser == null) {
            throw new NotRegisteredUserException();
        }

        if(!searchedUser.comparePassword(user.getPassword())) {
            throw new WrongPasswordException();
        }

        searchedUser.setOnline(true);
        getOnlineUserList().put(name, searchedUser);

        return searchedUser;
    }

    public static boolean isLoggedIn(User user) {
       return getOnlineUserList().containsKey(user.login);
    }

    /*
    |-----------------------------------------------------------------------------------------
    | GETTERS & SETTERS
    |-----------------------------------------------------------------------------------------
    */
    public static Map<String, User> getRegisteredUserList() {
        return UserList.registeredUserList;
    }

    public static void setRegisteredUserList(Map<String, User> registeredUserList) {
        UserList.registeredUserList = registeredUserList;
    }

    public static Map<String, User> getOnlineUserList() {
        return onlineUserList;
    }

    public static void setOnlineUserList(Map<String, User> onlineUserList) {
        UserList.onlineUserList = onlineUserList;
    }


    public static User findUser(String login) {

        for (Map.Entry<String, User> user: registeredUserList.entrySet()) {
            if(user.getValue().getLogin().equals(login)) {
                return user.getValue();
            }
        }

        return null;
    }
    /*
    |-----------------------------------------------------------------------------------------
    | OTHER
    |-----------------------------------------------------------------------------------------
    */
    public static void print() {
        for (Map.Entry<String, User> entry : getRegisteredUserList().entrySet()) {
            System.out.println("RegisteredUserList:begin");
            System.out.println(entry.getKey() + " " + entry.getValue());
            System.out.println("RegisteredUserList:end");
        }
    }

}
