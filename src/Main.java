import Manager.Managers;
import Model.Task;
import Model.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var managers = Managers.getDefault();
        System.out.println(managers.getAllTasks());
        System.out.println("-- Создание 1 простой задачи, 2 эпика с 2 подзадачами.");
        managers.newTask("Task 1", "Description of Task 1");
        managers.newEpic("Epic 1", "Description of Epic 1");
        managers.newSubtask(managers.getTaskIdByName("Epic 1"), "Sub 1", "Description Sub 1");
        managers.newSubtask(managers.getTaskIdByName("Epic 1"), "Sub 2", "Description Sub 2");
        managers.newEpic("Epic 2", "Description of Epic 2");
        managers.newSubtask(managers.getTaskIdByName("Epic 2"), "Sub 1", "Description Sub 1");
        managers.newSubtask(managers.getTaskIdByName("Epic 2"), "Sub 2", "Description Sub 2");

        System.out.println("-- Печать всех задач");
        System.out.println(managers.getTasks().values());
        System.out.println("-- Получение списка всех задач");
        System.out.println(managers.getAllTasks().values());
        System.out.println();

        System.out.println("-- Поиск задачи номер 3 и номер 9");
        if (managers.getTaskById(3) != null)
            System.out.println("Задача найдена");
        else System.out.println("null");
        if (managers.getTaskById(9) != null)
            System.out.println(managers.getTaskById(9).toString());
        else System.out.println("null");

        System.out.println("-- История запросов");
        for (Task task : managers.getHistory()) {
            System.out.println(task.getTaskIdNumber() + " " + task.getTaskTitle());
        }
        System.out.println("-- Замена простой задачи и 2-х подзадач в эпике.");
        managers.updateTask(1, "Task1-2", "Description of Task 1-2", TaskStatus.DONE);
        managers.updateTask(3, "Sub 1-2", "Description Sub 1-2", TaskStatus.IN_PROGRESS);
        managers.updateTask(4, "Sub 2-2", "Description Sub 2-2", TaskStatus.DONE);

        System.out.println("-- Печать всех задач");
        System.out.println(managers.getTasks().values());

        System.out.println("-- Очистка задачи 4 из эпика");
        managers.removeTaskById(4);

        System.out.println("-- Печать подзадач из эпика");
        if (managers.getSubTasksOfEpicById(2) != null)
            System.out.println(managers.getSubTasksOfEpicById(2).values());
        else System.out.println("null");

        System.out.println("-- Замена эпика с сохранением подзадач.");
        managers.updateTask(2, "Epic 1-2", "Description of Epic 1-2", true);

        System.out.println("-- Печать всех задач");
        System.out.println(managers.getTasks().values());

        System.out.println("-- вызов задач");
        List<Task> list = new ArrayList<>();
        list.add(managers.getTaskById(1));
        list.add(managers.getTaskById(1));
        list.add(managers.getTaskById(5));
        list.add(managers.getTaskById(5));
        list.add(managers.getTaskById(2));
        list.add(managers.getTaskById(5));
        list.add(managers.getTaskById(6));
        list.add(managers.getTaskById(1));
        list.add(managers.getTaskById(1));
        list.add(managers.getTaskById(2));
        list.add(managers.getTaskById(2));
        System.out.println("-- История запросов");
        for (Task task : Managers.getDefaultHistory()) {
            System.out.println(task.getTaskIdNumber() + " " + task.getTaskTitle());
        }

        System.out.println("-- Замена эпика без сохранения подзадач.");
        managers.updateTask(2, "Epic 1-3", "Description of Epic 1-3", false);

        System.out.println("-- Печать всех задач");
        System.out.println(managers.getTasks().values());

        System.out.println("-- Очистка всех задач");
        managers.clearAllTasks();

        System.out.println("-- Печать всех задач");
        System.out.println(managers.getTasks().values());
    }
}