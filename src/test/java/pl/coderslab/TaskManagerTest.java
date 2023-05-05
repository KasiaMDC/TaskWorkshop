package pl.coderslab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskManagerTest {
    private TaskManager underTest;

    @Before
    public void setUp() {
        underTest = new TaskManager();
    }

    @Test
    public void shouldAddATask() {
        // given
        String[] newTask = new String []{"0", "Task name", "2023-02-02", "important"};

        // when
        underTest.addTask(newTask);

        // then
        assertEquals(1, underTest.getTaskCount());
        //task = underTest.getTask(0)
        //        assertEquals(task.getName(), "Task name");
    }
}