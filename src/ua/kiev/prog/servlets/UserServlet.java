package ua.kiev.prog.servlets;

import ua.kiev.prog.lists.UserList;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Http;
import ua.kiev.prog.utils.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length < 2) {
            Http.sendResponse(
                    resp,
                    HttpServletResponse.SC_BAD_REQUEST,
                    new JsonResponse(HttpServletResponse.SC_BAD_REQUEST, "User login required").toJSON());
            return;
        }

        String login = pathParts[1];

        User user = UserList.findUser(login);

        if(user != null) {
            Http.sendResponse(resp, 200, user.toJSON());
        } else {
            JsonResponse jsonResp = JsonResponse.getInstance(HttpServletResponse.SC_NOT_FOUND, "User not found");
            Http.sendResponse(resp, HttpServletResponse.SC_NOT_FOUND, jsonResp.toJSON());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length < 2) {
            Http.sendResponse(
                    resp,
                    HttpServletResponse.SC_BAD_REQUEST,
                    new JsonResponse(HttpServletResponse.SC_BAD_REQUEST, "User login required").toJSON());
            return;
        }

        String login = pathParts[1];

        User user = UserList.findUser(login);

        if(user != null && user.isOnline()) {
            String json = Http.requestBodyToJson(req);
            String room = User.fromJSON(json).getRoom();
            user.setRoom(room);
            Http.sendResponse(resp, 200, user.toJSON());
        } else {
            JsonResponse jsonResp = JsonResponse.getInstance(HttpServletResponse.SC_NOT_FOUND, "User not found");
            Http.sendResponse(resp, HttpServletResponse.SC_NOT_FOUND, jsonResp.toJSON());
        }
    }
}
