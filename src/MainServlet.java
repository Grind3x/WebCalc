import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet", urlPatterns = "/")
public class MainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                "  <meta charset=\"utf-8\" />\n" +
                "  <title>WebCalculator</title>\n" +
                "  <style>\n" +
                "  </style>\n" +
                " </head>\n" +
                " <body>\n";
        String errorMsg = "";
        String form = "   <form action=\"/result\" method=\"post\">\n" +
                "     <label>Number 1:  <input type=\"text\" name=\"numberOne\"><br></label>\n" +
                "     <label>Number 2:  <input type=\"text\" name=\"numberTwo\"><br></label>\n" +
                "     <br>\n" +
                "     |<input type=\"radio\" name=\"action\" value=\"+\" checked> + \n" +
                "     |<input type=\"radio\" name=\"action\" value=\"-\"> - \n" +
                "     |<input type=\"radio\" name=\"action\" value=\"/\"> / \n" +
                "     |<input type=\"radio\" name=\"action\" value=\"*\"> * |\n" +
                "     <input type=\"submit\" value=\"Calculate\">\n" +
                "   </form>\n";
        String footer = " </body>\n" +
                "</html>\n";
        String error = "";

        try {
            error = req.getAttribute("error").toString();
            errorMsg = "<h4 style=color:red>Incorrect numbers!</h3>";
        } catch (NullPointerException e) {
        }

        PrintWriter pw = resp.getWriter();
        pw.println(header + errorMsg + form + footer);
        pw.close();
    }
}
