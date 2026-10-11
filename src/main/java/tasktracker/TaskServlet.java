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

    private static final long serialVersionUID = 1L;

    private static final List<String> tasks = new ArrayList<>();
    private static final List<String> archivedTasks = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        tasks.add("Complete programming assignment - High");
        tasks.add("Study for upcoming exam - Medium");
        tasks.add("Review project requirements - Low");
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
        out.println("<input type='hidden' name='action' value='add'>");
        out.println("<input type='text' name='task' placeholder='Enter a task'>");
        out.println("<select name='priority'>");
        out.println("<option value='Low'>Low</option>");
        out.println("<option value='Medium'>Medium</option>");
        out.println("<option value='High'>High</option>");
        out.println("</select>");
        out.println("<button type='submit'>Add Task</button>");
        out.println("</form>");
        out.println("<h2>My Tasks</h2>");
        out.println("<p>Total Tasks: " + tasks.size() + "</p>");
        out.println("<ul>");

        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i);

            out.println("<li>");
            out.println(task);
            out.println("<form method='post' action='TaskServlet' style='display:inline;'>");
            out.println("<input type='hidden' name='action' value='complete'>");
            out.println("<input type='hidden' name='taskIndex' value='" + i + "'>");
            out.println("<button type='submit'>Complete</button>");
            out.println("</form>");
            out.println("</li>");
        }

        out.println("</ul>");
        out.println("<h2>Archived Tasks</h2>");
        out.println("<ul>");

        if (archivedTasks.isEmpty()) {
            out.println("<li>No archived tasks yet.</li>");

        } else {
            for (String task : archivedTasks) {
                out.println("<li>" + task + "</li>");
            }
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String task = request.getParameter("task");
            String priority = request.getParameter("priority");

            if (task != null && !task.trim().isEmpty()
                    && priority != null && !priority.trim().isEmpty()) {

                tasks.add(task.trim() + " - " + priority);
            }
        } else if ("complete".equals(action)) {

            String taskIndex = request.getParameter("taskIndex");

            if (taskIndex != null) {
                int index = Integer.parseInt(taskIndex);
                
                if (index >= 0 && index < tasks.size()) {
                    String completedTask = tasks.remove(index);
                    archivedTasks.add(completedTask);
                }
            }
        }
        response.sendRedirect("TaskServlet");
    }
}