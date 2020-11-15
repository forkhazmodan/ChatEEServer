package ua.kiev.prog.servlets;

import ua.kiev.prog.lists.MessageList;
import ua.kiev.prog.models.Message;
import ua.kiev.prog.utils.Http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MessageListServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();


}
