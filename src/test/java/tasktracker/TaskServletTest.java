package tasktracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskServletTest
{
    @Test
    public void testInitialTaskCount()
    {
        TaskManager taskManager = new TaskManager();

        assertEquals(3, taskManager.getTaskCount());
    }

    @Test
    public void testAddTask()
    {
        TaskManager taskManager = new TaskManager();

        boolean result = taskManager.addTask("Finish homework", "High");

        assertTrue(result);
        assertEquals(4, taskManager.getTaskCount());
        assertTrue(taskManager.getTasks().contains("Finish homework - High"));
    }

    @Test
    public void testAddTaskWithMediumPriority()
    {
        TaskManager taskManager = new TaskManager();

        boolean result = taskManager.addTask("Study for exam", "Medium");

        assertTrue(result);
        assertTrue(taskManager.getTasks().contains("Study for exam - Medium"));
    }

    @Test
    public void testRejectEmptyTask()
    {
        TaskManager taskManager = new TaskManager();

        boolean result = taskManager.addTask("", "High");

        assertFalse(result);
        assertEquals(3, taskManager.getTaskCount());
    }

    @Test
    public void testRejectWhitespaceTask()
    {
        TaskManager taskManager = new TaskManager();

        boolean result = taskManager.addTask("   ", "Low");

        assertFalse(result);
        assertEquals(3, taskManager.getTaskCount());
    }
}
