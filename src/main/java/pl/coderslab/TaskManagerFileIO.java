package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManagerFileIO {

    private TaskManager taskManager;

    public TaskManagerFileIO(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    String[][] readFromFile(String filename) {

        String[][] result = new String[0][];

        File file = new File(filename);

        try (Scanner scan = new Scanner(file)) {
            scan.useDelimiter("\\s*[\\,\\n]\\s*");
            while (scan.hasNextLine()) {
                // skip empty line
                if (!scan.hasNext()) {
                    break;
                }

                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = new String[4];
                for (int i = 0; i < 4; i++) {
                    String item = scan.next();
                    result[result.length - 1][i] = item;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
        }

        return result;
    }

    void writeToFile(String filename) {
        writeToFile(filename, taskManager.tasks);
    }

    void writeToFile(String filename, String[][] arr) {
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            for (int i = 0; i < arr.length; i++) {
                fileWriter.append(String.format("%s,%s,%s,%s\n",i,arr[i][1],arr[i][2],arr[i][3]));
            }
        } catch (IOException ex) {
            System.out.println("Błąd zapisu do pliku.");
        }
    }
}
