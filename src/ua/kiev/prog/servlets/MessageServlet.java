package ua.kiev.prog.servlets;

import ua.kiev.prog.lists.MessageList;
import ua.kiev.prog.lists.UserList;
import ua.kiev.prog.models.Message;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Http;
import ua.kiev.prog.utils.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bufStr = Http.requestBodyToJson(req);

        Message msg = Message.fromJSON(bufStr);
        if (msg != null) {
            msgList.add(msg);
            Http.sendResponse(resp, HttpServletResponse.SC_OK, msg.toJSON());
        } else {
            Http.sendResponse(resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fromStr = req.getParameter("from");
        String login = req.getParameter("login");
        User user = UserList.findUser(login);

        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) from = 0;
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (user == null) {
            JsonResponse jsonResponse = JsonResponse.getInstance(HttpServletResponse.SC_BAD_REQUEST, "User not found");
            Http.sendResponse(resp, HttpServletResponse.SC_BAD_REQUEST, jsonResponse.toJSON());
            return;
        }

//        String json = msgList.getMessagesJson(msgList.getMessagesMap(from, user));
        String json = msgList.getMessagesJson(msgList.getMessagesList(from, user));

        if (json != null) {
            Http.sendResponse(resp, HttpServletResponse.SC_OK, json);
        }
    }
}
