package ua.kiev.prog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Http {
    public static byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

    public static String requestBodyToJson(HttpServletRequest req) throws IOException {
        byte[] buf = Http.requestBodyToArray(req);
        return new String(buf, StandardCharsets.UTF_8);
    }

    public static void sendResponse(HttpServletResponse resp, int status, String body) throws IOException {

        resp.setContentType("application/json");
        resp.setStatus(status);

        OutputStream os = resp.getOutputStream();
        byte[] buf = body.getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }

    public static void sendResponse(HttpServletResponse resp, int status) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(status);
    }

}
