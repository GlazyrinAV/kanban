import Manager.Managers;
import Manager.TaskManager;
import Server.KVServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskManager manager2 = Managers.getWithAutoSave();
//        manager2.newSimpleTask( new NewTask("1", "1", LocalDateTime.of(2023, Month.MARCH, 13, 15,00), 30));
//        manager2.newSimpleTask( new NewTask("1", "1", LocalDateTime.of(2023, Month.MARCH, 13, 15,00), 30));

//        new HttpTaskServer().startTasksServer();
        new KVServer().start();
//
//        var manager3 = Managers.getDefaultWithTimePeriods();
////        manager3.newSimpleTask(new NewTask("1", "1", LocalDateTime.of(2023, Month.FEBRUARY, 28, 21, 53), 30));
////        manager3.newSimpleTask(new NewTask("2", "2", LocalDateTime.of(2023, Month.FEBRUARY, 27, 21, 52), 30));
////        manager3.newEpic(new NewTask("3", "3"));
////        manager3.newSubtask(new NewTask("4", "4"), 3);
////        manager3.newSubtask(new NewTask("5", "5", LocalDateTime.now().minusDays(1), 60), 3);
//        manager3.newSimpleTask(new NewTask("1", "1",
//                LocalDateTime.of(2023, Month.APRIL, 1, 0, 0), 30));
//        manager3.newSimpleTask(new NewTask("2", "2",
//                LocalDateTime.of(2023, Month.APRIL, 3, 0, 10), 10));
//        manager3.newSimpleTask(new NewTask("1", "1",
//                LocalDateTime.of(2023, Month.APRIL, 2, 0, 0), 30));
//        manager3.newSimpleTask(new NewTask("2", "2",
//                LocalDateTime.of(2023, Month.APRIL, 10, 0, 10), 10));
//        System.out.println(manager3.getAllTasks().values());
//        System.out.println(Arrays.toString(manager3.getPrioritizedTasks().toArray()));
//
//        var managers = Managers.getWithAutoSave();
//        System.out.println("-- История запросов");
//        for (Integer taskId : managers.getHistory()) {
//            System.out.print(taskId + ", ");
//        }
//        System.out.println("-- Получение списка всех задач");
//        System.out.println(managers.getAllTasks().values());
//        System.out.println();
//        System.out.println("-- Создание 1 простой задачи, 2 эпика с 2 подзадачами.");
//        managers.newSimpleTask(new NewTask("1", "1"));
//        managers.getTaskById(1);
//        managers.newSimpleTask(new NewTask("Task 1", "Description of Task 1"));
//        managers.newEpic(new NewTask("Epic 1", "Description of Epic 1"));
//        managers.newSubtask(new NewTask("Sub 1", "Description Sub 1"), managers.getTaskIdByName("Epic 1"));
//        managers.newSubtask(new NewTask("Sub 2", "Description Sub 2"), managers.getTaskIdByName("Epic 1"));
//        managers.newEpic(new NewTask("Epic 2", "Description of Epic 2"));
//        managers.newSubtask(new NewTask("Sub 1", "Description Sub 1"), managers.getTaskIdByName("Epic 2"));
//        managers.newSubtask(new NewTask("Sub 2", "Description Sub 2"), managers.getTaskIdByName("Epic 2"));
//
//        System.out.println("-- Получение списка всех задач");
//        System.out.println(managers.getAllTasks().values());
//        System.out.println();
//
//        System.out.println("-- Поиск задачи номер 1 и номер 9");
//        if (managers.getTaskById(1) != null)
//            System.out.println("Задача найдена");
//        else System.out.println("null");
//        if (managers.getTaskById(9) != null)
//            System.out.println(managers.getTaskById(9).toString());
//        else System.out.println("null");
//
//        for (Integer taskId : managers.getHistory()) {
//            System.out.print(taskId + ", ");
//        }
//        System.out.println("-- Замена простой задачи и 2-х подзадач в эпике.");
//        managers.updateTask(managers.getTaskIdByName("Task 1"), TaskStatus.DONE);
//        managers.updateTask(managers.getTaskIdByName("Sub 1"), TaskStatus.IN_PROGRESS);
//        managers.updateTask(managers.getTaskIdByName("Sub 2"), TaskStatus.DONE);
//
//        System.out.println("-- Печать всех задач");
//        System.out.println(managers.getAllTasks().values());
//
//        System.out.println("-- Очистка задачи 4 из эпика");
//        managers.removeTaskById(4);
//
//        System.out.println("-- Печать подзадач из эпика");
//        if (managers.getSubTasksOfEpicById(2) != null)
//            System.out.println(managers.getSubTasksOfEpicById(2));
//        else System.out.println("null");
//
//        System.out.println("-- Замена эпика с сохранением подзадач.");
//        managers.updateTask(2, true);
//
//        System.out.println("-- Печать всех задач");
//        System.out.println(managers.getAllTasks().values());
//
//        System.out.println("-- вызов 14 задач");
//        managers.getTaskById(1);
//        managers.getTaskById(1);
//        managers.getTaskById(5);
//        managers.getTaskById(5);
//        managers.getTaskById(2);
//        managers.getTaskById(5);
//        managers.getTaskById(6);
//        managers.getTaskById(1);
//        managers.getTaskById(1);
//        managers.getTaskById(2);
//        managers.getTaskById(2);
//        managers.getTaskById(1);
//        managers.getTaskById(1);
//        managers.getTaskById(3);
//        managers.getTaskById(11);
//        System.out.println("-- История запросов");
//        for (Integer taskId : managers.getHistory()) {
//            System.out.print(taskId + ", ");
//        }
//
//        System.out.println("-- Замена эпика без сохранения подзадач.");
//        managers.updateTask(2, false);
//
//        System.out.println("-- Печать всех задач");
//        System.out.println(managers.getAllTasks().values());
//
//        var managers2 = Managers.getDefault();
//        System.out.println("--Перезапуск системы");
//        System.out.println("-- История запросов");
//        for (Integer taskId : managers.getHistory()) {
//            System.out.print(taskId + ", ");
//        }
//        managers2.newSimpleTask(new NewTask("newSimple", "JustSimple"));
//        System.out.println(managers2.getAllTasks().values());
//        managers2.getTaskById(managers2.getTaskIdByName("newSimple"));
//        System.out.println("-- История запросов");
//        for (Integer taskId : managers.getHistory()) {
//            System.out.print(taskId + ", ");
//        }
//
//        System.out.println("-- Очистка всех задач");
//        managers2.clearAllTasks();
//        System.out.println("-- Печать всех задач");
//        System.out.println(managers2.getAllTasks().values());
//        managers2.newEpic(new NewTask("NewEpic", "NewEpicDescr"));
//        managers2.newSubtask(new NewTask("NewSub", "SubDes"), managers2.getTaskIdByName("NewEpic"));
//        System.out.println("-- Печать всех задач");
//        System.out.println(managers2.getAllTasks().values());
//        System.out.println("-- История запросов");
//        for (Integer taskId : managers.getHistory()) {
//            System.out.print(taskId + ", ");
//        }
    }
}