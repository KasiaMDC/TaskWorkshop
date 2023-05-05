package pl.coderslab;

import java.io.Closeable;
import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskCLI implements Closeable {

    private TaskManager taskManager;
    private Scanner scan;

    public TaskCLI(TaskManager taskManager) {
        this.taskManager = taskManager;
        scan = new Scanner(System.in);
    }

    @Override
    public void close() throws IOException {
        scan.close();
    }

    public void printOptions() {
        System.out.println(ConsoleColors.RESET + "Available options: add, list, delete, quit");
    }

    // Reads user input such as add, list, remove, quit
    // and calls the right method to handle it
    // Returns false if the program should end, true otherwise
    public boolean processUserInput() {
        String userInp = "";

        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        printOptions();
        userInp = scan.nextLine();

        switch (userInp) {
            case "add":
                taskManager.addTask(readTaskFromUserInput());
                break;
            case "delete":
                taskManager.removeTask(readTaskToBeRemoved());
                break;
            case "list":
                taskManager.listTasks();
                break;
            case "quit":
                System.out.println(ConsoleColors.RED + "Bye, bye!");
                return false;
            default:
                System.out.println("Please select a correct option.");
        }

        return true;
    }

    public String[] readTaskFromUserInput() {
        String taskDescription = "";
        String date = "";
        String taskImportance = "";

        do {
            System.out.println("Please add task description (without commas)");
            taskDescription = scan.nextLine();
        } while ((!validateCommasFromUserInput(taskDescription)) || (!validateInputIsNotEmpty(taskDescription)));

        do {
            System.out.println("Please add task due date (yyyy-mm-dd)");
            date = scan.nextLine();
        } while ((!validateDateFromUserInput(date)) || (!validateCommasFromUserInput(date)) || (!validateInputIsNotEmpty(date)));

        do {
            System.out.println("Is your task important? true/false");
            taskImportance = scan.nextLine();
        }
        while ((!validateInputIsNotEmpty(taskImportance)) || (!validateCommasFromUserInput(taskImportance)));

        return new String[]{String.valueOf(taskManager.getTaskCount()), taskDescription, date, taskImportance};
    }


    // returns true if the user input is ok, false otherwise
    boolean validateCommasFromUserInput(String input) {
        boolean hasCommas = Pattern.matches(".*,.*", input);

        return !hasCommas;
    }

    // Example input: 2010-10-22
    public boolean validateDateFromUserInput(String input) {
        //return Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}",input);

        try {
            String year = input.substring(0, 4);
            String month = input.substring(5, 7);
            String day = input.substring(8, 10);

            int ofYear = Integer.parseInt(year);
            int ofMonth = Integer.parseInt(month);
            if (ofMonth > 12 || ofMonth <= 0)
                return false;
            int ofDay = Integer.parseInt(day);
            if (ofDay > 31 || ofDay <= 0)
                return false;
        } catch (Exception e) {
            return false;
        }

        //boolean hasDash = Pattern.matches("-", input);

        return true;
    }

    public boolean validateInputIsNotEmpty(String input) {
        boolean isEmpty = input.isEmpty();

        return !isEmpty;
    }

    public int readTaskToBeRemoved() {
        System.out.println("Which task should be removed?");
        int toBeRemoved = 0;
        do {
            try {
                toBeRemoved = scan.nextInt();
                scan.nextLine();
                if (toBeRemoved < 0 || toBeRemoved > taskManager.getTaskCount()) {
                    throw new RuntimeException();
                } else {
                    return toBeRemoved;
                }
            } catch (Exception e) {
                System.out.println("Incorrect number. Please give number greater or equal 0");
                //System.out.println(e.getMessage());
            }
        }
        while (toBeRemoved < 0 || toBeRemoved > taskManager.getTaskCount());

        return -1;
    }
}