package ua.kiev.prog.lists;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.JsonMessages;
import ua.kiev.prog.models.Message;
import ua.kiev.prog.models.User;

public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private final List<Message> list = new LinkedList<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    @Deprecated
    public List<Message> getMessages(int from) {
        if (from >= list.size()) return null;

        List<Message> newList = new ArrayList<>();

        for (int i = from; i < list.size(); i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    @Deprecated
    public Map<Integer, Message> getMessagesMap(int from) {

         Map<Integer, Message> newMap = new HashMap<>();

        for (int i = from; i < list.size(); i++) {
            newMap.put(i, list.get(i));
        }

        return newMap;
    }

    public List<Message> getMessagesList(int from, User user) {

        List<Message> newList = new LinkedList<>();

        for (int i = from; i < list.size(); i++) {
            Message message = list.get(i);
            String room = user.getRoom();
            String login = user.getLogin();

            if(
                (room != null && room.equals(message.getRoom())) ||
                (login != null && login.equals(message.getTo())) ||
                (message.getTo() == null)
            ) {
                message.setId(i);
                newList.add(message);
            }
        }

        return newList;
    }

    @Deprecated
    public Map<Integer, Message> getMessagesMap(int from, User user) {

         Map<Integer, Message> newMap = new HashMap<>();

        for (int i = from; i < list.size(); i++) {
            Message message = list.get(i);
            String room = user.getRoom();
            String login = user.getLogin();

            if(
                (room != null && room.equals(message.getRoom())) ||
                (login != null && login.equals(message.getTo()))
            ) {
                message.setId(i);
                newMap.put(i, message);
            }
        }

        return newMap;
    }

    public synchronized String getMessagesJson(List<Message> newList) {
        return new Gson().toJson(newList);
    }

    @Deprecated
    public synchronized String getMessagesJson(Map<Integer, Message> newMap) {
        return new Gson().toJson(newMap);
    }

    public synchronized String toJSON(int n) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, n));
    }
}
