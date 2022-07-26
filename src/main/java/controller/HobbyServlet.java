package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * author  luhongtao
 * 2022/7/25 14:24:20
 **/
@WebServlet(value = "/hobby")
public class HobbyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(0);
        List<String> cityInfo = jedis.lrange("hobby", 0, -1);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(cityInfo);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes());
        out.flush();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
