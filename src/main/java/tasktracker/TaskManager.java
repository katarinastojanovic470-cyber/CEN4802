package tasktracker;

import java.util.ArrayList;
import java.util.List;

public class TaskManager
{
    private final List<String> tasks = new ArrayList<>();

    public TaskManager()
    {
        tasks.add("Complete programming assignment - High");
        tasks.add("Study for upcoming exam - Medium");
        tasks.add("Review project requirements - Low");
    }

    public boolean addTask(String task, String priority)
    {
        if (task != null && !task.trim().isEmpty())
        {
        	tasks.add(task.trim() + " - " + priority);
            return true;
        }

        return false;
    }

    public int getTaskCount()
    {
        return tasks.size();
    }

    public List<String> getTasks()
    {
        return new ArrayList<>(tasks);
    }
}