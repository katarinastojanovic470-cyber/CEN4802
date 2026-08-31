package tasktracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    private static final List<String> tasks = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        tasks.add("Complete programming assignment");
        tasks.add("Study for upcoming exam");
        tasks.add("Review project requirements");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Task Tracker</title></head>");
        out.println("<body>");

        out.println("<h1>Task Tracker</h1>");

        out.println("<h2>Add a Task</h2>");

        out.println("<form method='post' action='TaskServlet'>");

        out.println("<input type='text' name='task' placeholder='Enter a task'>");

        out.println("<select name='priority'>");
        out.println("<option value='Low'>Low</option>");
        out.println("<option value='Medium'>Medium</option>");
        out.println("<option value='High'>High</option>");
        out.println("</select>");

        out.println("<button type='submit'>Add Task</button>");

        out.println("</form>");

        out.println("<h2>My Tasks</h2>");
        out.println("<ul>");

        for (String task : tasks) {
            out.println("<li>" + task + "</li>");
        }

        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String task = request.getParameter("task");
        String priority = request.getParameter("priority");

        if (task != null && !task.trim().isEmpty()) {
            tasks.add(task.trim() + " - " + priority);
        }

        response.sendRedirect("TaskServlet");
    }
}