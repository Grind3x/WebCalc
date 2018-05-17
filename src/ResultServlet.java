import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResultServlet", urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    private static final String HEADER = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<style>\n" +
            "body {\n" +
            "  margin: 0;\n" +
            "  font-family: Arial, Helvetica, sans-serif;\n" +
            "}\n" +
            "\n" +
            ".topnav {\n" +
            "\tcolor: white;\n" +
            "    overflow: hidden;\n" +
            "    background-color: lightblue;;\n" +
            "}\n" +
            "\n" +
            ".content {\n" +
            "    background-color: #ddd;\n" +
            "    padding: 10px;\n" +
            "}\n" +
            "\n" +
            ".footer {\n" +
            "\tcolor: white;\n" +
            "    background-color: lightblue;\n" +
            "    padding: 1px;\n" +
            "}\n" +
            "</style>" +
            " <head>\n" +
            "  <meta charset=\"utf-8\" />\n" +
            "  <title>WebCalculator</title>\n" +
            "  <style>\n" +
            "  </style>\n" +
            " </head>\n" +
            " <body>\n" +
            "<div class=\"topnav\">\n" +
            "  <h3>Simple Web calculator</h3>\n" +
            "</div><div class=\"content\">";
    private static final String FOOTER = "</div><div class=\"footer\">\n" +
            "  <p>&#9400; Grind3x</p>\n" +
            "</div> </body>\n" +
            "</html>\n";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = "<h4 style=\"color:red\">You must go to the <a href=\"/\">start page</a></h4>";
        PrintWriter pw = resp.getWriter();
        pw.write(HEADER + body + FOOTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double numberOne = 0.0;
        Double numberTwo = 0.0;

        try {
            numberOne = Double.valueOf(req.getParameter("numberOne"));
            numberTwo = Double.valueOf(req.getParameter("numberTwo"));
        } catch (NumberFormatException e) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("/").forward(req, resp);
        }

        Double result = 0.0;
        String sign = req.getParameter("action");
        PrintWriter pw = resp.getWriter();
        switch (sign) {
            case "+":
                result = numberOne + numberTwo;
                break;
            case "-":
                result = numberOne - numberTwo;
                break;
            case "*":
                result = numberOne * numberTwo;
                break;
            case "/":
                try {
                    result = numberOne / numberTwo;
                } catch (ArithmeticException e) {
                    req.setAttribute("error", "1");
                    req.getRequestDispatcher("/").forward(req, resp);
                }
                break;
        }

        String body = "Result = " + result + "<br><br><a href=\"/\">Back</a>";
        pw.write(HEADER + body + FOOTER);
    }
}
