package ua.kiev.prog.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.lists.UserList;
import ua.kiev.prog.utils.Http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String body = gson.toJson(UserList.getOnlineUserList());

        Http.sendResponse(resp, HttpServletResponse.SC_OK, body);
    }
}
