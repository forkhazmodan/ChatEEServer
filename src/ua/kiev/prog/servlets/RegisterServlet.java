package ua.kiev.prog.servlets;

import ua.kiev.prog.exceptions.DuplicateUserException;
import ua.kiev.prog.lists.UserList;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String json = Http.requestBodyToJson(req);
        User user = User.fromJSON(json);

        if (json != null) {

            try {
                UserList.register(user);
            } catch (DuplicateUserException e) {
                Http.sendResponse(resp, HttpServletResponse.SC_BAD_REQUEST, e.toJSON());
                return;
            }
            Http.sendResponse(resp, HttpServletResponse.SC_OK, user.toJSON());
        }
    }
}
