package Tests;

import Manager.FileBackedTasksManager;
import Manager.InMemoryHistoryManager;
import Manager.TaskManager;
import Model.NewTask;
import Model.Task;
import Model.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManagerTest extends TaskManagerTest<TaskManager> {

    private final Path dataFile = Path.of("./Resources/Data.csv");
    private Path testFile;

    @BeforeEach
    public void createTaskManager() throws IOException {
        resetIdCounter();
        deleteDataForTest();
        setManager(new FileBackedTasksManager(new InMemoryHistoryManager()));
    }

    // Запись
    // 1. Запись обычная с историей
    @Test
    public void dataWriteBase() throws IOException {
        testFile = Path.of("./ResourcesForTest/Test1.csv");
        getTestManager().newSimpleTask(new NewTask("1", "1"));
        getTestManager().getTaskById(1);
        Assertions.assertTrue(isTwoFilesAreEqual(dataFile, testFile),
                "Ошибка при запаси в файл задачи и истории.");
    }

    // 2. Запись при пустом списке
    @Test
    public void dataWriteWithNoTasks() throws IOException {
        testFile = Path.of("./ResourcesForTest/Test2.csv");
        Assertions.assertTrue(isTwoFilesAreEqual(dataFile, testFile),
                "Ошибка записи в файл при отсутствии данных.");
    }

    // 3. Запись эпика без подзадач
    @Test
    public void dataWriteWithEpicWithNoSubTasks() throws IOException {
        testFile = Path.of("./ResourcesForTest/Test3.csv");
        getTestManager().newEpic(new NewTask("1", "1"));
        Assertions.assertTrue(isTwoFilesAreEqual(dataFile, testFile),
                "Ошибка записи данных в файл для эпика без подзадач.");
    }

    // 4. Запись без истории
    @Test
    public void dataWriteWithNoHistory() throws IOException {
        testFile = Path.of("./ResourcesForTest/Test4.csv");
        getTestManager().newSimpleTask(new NewTask("1", "1"));
        Assertions.assertTrue(isTwoFilesAreEqual(dataFile, testFile),
                "Ошибка записи данных в файл без истории");
    }

    // Чтение
    // 1. Чтение пустого файла
    @Test
    public void dataReadFromFileWithNoData() {
        boolean isTasksEmpty = getTestManager().getAllTasks().isEmpty();
        boolean isHistoryEmpty = getTestManager().getHistory().isEmpty();
        Assertions.assertTrue(isTasksEmpty && isHistoryEmpty,
                "Ошибка чтении данных из пустого файла.");
    }

    // 2. Чтение файла с данными и историей
    @Test
    public void dataReadFromFileWithDataAndHistory() {
        getTestManager().newSimpleTask(new NewTask("1", "1"));
        getTestManager().newSimpleTask(new NewTask("2", "2"));
        getTestManager().updateTask(1, TaskStatus.IN_PROGRESS);
        getTestManager().getTaskById(1);
        setManager(new FileBackedTasksManager(new InMemoryHistoryManager()));
        boolean isHistoryIsPresent = getTestManager().getHistory().equals(new ArrayList<>(List.of(1)));
        boolean isTaskIsPresent =
                checkTask(getTestManager().getTaskById(1), "1", "1", 1, TaskStatus.IN_PROGRESS) &&
                        checkTask(getTestManager().getTaskById(2), "2", "2", 2, TaskStatus.NEW);
        Assertions.assertTrue(isHistoryIsPresent && isTaskIsPresent,
                "Ошибка чтении данных из файла с задачами и историей.");
    }

    // 3. Чтение данных без истории
    @Test
    public void dataReadFromFileWithDataAndNoHistory() {
        getTestManager().newSimpleTask(new NewTask("1", "1"));
        getTestManager().newSimpleTask(new NewTask("2", "2"));
        setManager(new FileBackedTasksManager(new InMemoryHistoryManager()));
        boolean isHistoryIsPresent = getTestManager().getHistory().equals(new ArrayList<>());
        boolean isTaskIsPresent =
                checkTask(getTestManager().getTaskById(1), "1", "1", 1, TaskStatus.NEW) &&
                        checkTask(getTestManager().getTaskById(2), "2", "2", 2, TaskStatus.NEW);
        Assertions.assertTrue(isHistoryIsPresent && isTaskIsPresent,
                "Ошибка чтении данных из файла с данными без истории.");
    }

    // 4. Чтение файла с эпиком без подзадач
    @Test
    public void dataReadFromFileWithEpicWithNoSubTasks() {
        getTestManager().newEpic(new NewTask("1", "1"));
        setManager(new FileBackedTasksManager(new InMemoryHistoryManager()));
        boolean isHistoryIsPresent = getTestManager().getHistory().equals(new ArrayList<>());
        boolean isTaskIsPresent =
                checkTask(getTestManager().getTaskById(1), "1", "1", 1, TaskStatus.NEW);
        Assertions.assertTrue(isHistoryIsPresent && isTaskIsPresent,
                "Ошибка чтении данных из файла об эпике без подзадач.");
    }

    private void resetIdCounter() {
        Task.resetCounterForTest();
    }

    private void deleteDataForTest() throws IOException {
        Path dataFile = Path.of("./Resources/Data.csv");
        if (Files.exists(dataFile)) {
            Files.delete(dataFile);
        }
    }

    private boolean isTwoFilesAreEqual(Path path1, Path path2) throws IOException {
        final File file1 = path1.toFile();
        final File file2 = path2.toFile();
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1)); BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
            while (reader1.ready() && reader2.ready()) {
                String line1 = reader1.readLine();
                String line2 = reader2.readLine();
                if (!line1.equals(line2)) return false;
            }
        }
        return true;
    }

    private boolean checkTask(Task task, String title, String description, int id, TaskStatus status) {
        return (task.getTaskTitle().equals(title)) &&
                (task.getTaskDescription().equals(description)) &&
                (task.getTaskIdNumber() == id) &&
                (task.getTaskStatus().equals(status));
    }
}