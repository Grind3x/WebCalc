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
            " <head>\n" +
            "  <meta charset=\"utf-8\" />\n" +
            "  <title>WebCalculator</title>\n" +
            "  <style>\n" +
            "  </style>\n" +
            " </head>\n" +
            " <body>\n";
    private static final String FOOTER = " </body>\n" +
            "</html>\n";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = "<h4 style=\"color:red\">You must go to the <a href=\"/\">start page</a></h4>";
        PrintWriter pw = resp.getWriter();
        pw.write(HEADER + body + FOOTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer numberOne = 0;
        Integer numberTwo = 0;

        try {
            numberOne = Integer.valueOf(req.getParameter("numberOne"));
            numberTwo = Integer.valueOf(req.getParameter("numberTwo"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "1");
            req.getRequestDispatcher("/").forward(req, resp);
        }

        Integer result = 0;
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

        String body = "Result = " + result + "<br><a href=\"/\">Back</a>";
        pw.write(HEADER + body + FOOTER);
    }
}
