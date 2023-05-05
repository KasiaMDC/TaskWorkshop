package pl.coderslab;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


public class App {

    static final String TASKS_FILENAME = "tasks.csv";

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        TaskManagerFileIO taskManagerFileIO = new TaskManagerFileIO(taskManager);

        try (TaskCLI taskCLI = new TaskCLI(taskManager)) {
            do {
                String[][] tasks = taskManagerFileIO.readFromFile(TASKS_FILENAME);
                taskManager.setTasks(tasks);

                if (!taskCLI.processUserInput()) {
                    return;
                }
                taskManagerFileIO.writeToFile(TASKS_FILENAME);
            }
            while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}