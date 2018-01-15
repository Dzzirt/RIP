package rip.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import rip.lab1.model.Gun;

import java.util.*;

import java.io.PrintWriter;

@WebServlet("/addAtomizer")
public class MainServlet extends HttpServlet {

    private ArrayList<Gun> mGuns = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        renderHeader(response);
        String action = (String) request.getParameter("action");

        if (action.equals("show")) {
            showGuns(response);
        } else {
            addGunsForm(response);
        }

        renderFooter(response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Gun gun = new Gun();

        gun.name = request.getParameter("name");
        gun.type = request.getParameter("type");
        gun.ammoCount = request.getParameter("ammoCount");
        gun.hasButt = Boolean.parseBoolean(request.getParameter("hasButt"));

        mGuns.add(gun);

        doGet(request, response);
    }

    private void showGuns(HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<table border=\"1\">\n" +
                "   <caption>Atomizer</caption>\n" +
                "   <thead>" +
                "   <tr>\n" +
                "    <th>Name</th>\n" +
                "    <th>Ammo count</th>\n" +
                "    <th>Type</th>\n" +
                "    <th>Has butt</th>\n" +
                "   </tr>" +
                "   </thead>" +
                "   <tbody>" +
                "   <tr>");
        for (Gun car : mGuns) {
            response.getWriter().println(
                    "<td>" + car.name + "</td>\n" +
                            "<td>" + car.ammoCount + "</td>\n" +
                            "<td>" + car.type + "</td>\n" +
                            "<td>" + car.hasButt + "</td>\n"
            );
        }
        response.getWriter().println("</tr></tbody></table");
    }

    private void addGunsForm(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        String htmlRespone =
                "    <form action=\"addGun?action=\" method=\"post\">\n" +
                        "        Name: <input type=\"text\" name=\"name\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                        "        Ammo count: <input type=\"text\" name=\"ammoCount\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                        "        Type: <input type=\"text\" name=\"type\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                        "        Has butt: <input type=\"text\" name=\"hasButt\" pattern=\"true|false\"/></br>\n" +
                        "        <input name=\"action\" type=\"hidden\" value=\"addBook\"/>" +
                        "        <input type=\"submit\" value=\"send\" />" +
                        "    </form>\n";
        writer.println(htmlRespone);
    }

    public void renderHeader(HttpServletResponse response) throws IOException {
        response.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n");
    }

    private void renderFooter(HttpServletResponse response) throws IOException {
        response.getWriter().println("</body></html>");
    }
}