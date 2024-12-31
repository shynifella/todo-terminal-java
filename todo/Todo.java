import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Todo {
    public static void main(String[] args) {

        Scanner uInput = new Scanner(System.in);

        ArrayList<String> todolist = new ArrayList<>();
        
        // Load existing tasks from the file
        loadTasks(todolist);

        int choice;

        System.out.println("Hello, fellow user! You shall track your activities with this terminal app!");
        help();

        choice = Integer.parseInt(uInput.nextLine());

        switch(choice) {
            case 1 -> Todo.addTask(todolist);
            case 2 -> Todo.deleteTask(todolist);
            case 3 -> Todo.viewTask(todolist);
            case 4 -> Todo.exit(uInput);
            default -> System.out.println("Unfortunately, there is no such option! :(");
        }
    }

    static void help() {
        System.out.println("Enter 1 to add");
        System.out.println("Enter 2 to delete");
        System.out.println("Enter 3 to view");
        System.out.println("Enter 4 to exit");
    }

    static void addTask(ArrayList<String> todolist) {
        Scanner uInput = new Scanner(System.in);
        System.out.println("Enter the task you want to add: ");
        String task = uInput.nextLine();
        todolist.add(task);
        saveTasks(todolist);
        System.out.println("Task added successfully!");
    }

    static void deleteTask(ArrayList<String> todolist) {
        Scanner uInput = new Scanner(System.in);
        System.out.println("Enter the task you want to delete: ");
        String task = uInput.nextLine();
        if (todolist.contains(task)) {
            todolist.remove(task);
            saveTasks(todolist);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    static void viewTask(ArrayList<String> todolist) {
        System.out.println("Your tasks are: ");
        for (String task : todolist) {
            System.out.println(task);
        }
    }

    public static void saveTasks(ArrayList<String> todolist) {
        String filePath = "todo-list/todo/data.txt";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(todolist);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
        }    
    }

    public static void loadTasks(ArrayList<String> todolist) {
        String filePath = "todo-list/todo/data.txt";
        File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
                todolist.addAll((ArrayList<String>) in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading tasks.");
                e.printStackTrace();
            }
        }
    }

    static void exit(Scanner uInput) {
        System.out.println("Goodbye! :)");
        uInput.close();
    }
}
