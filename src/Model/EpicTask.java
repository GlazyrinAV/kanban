package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class EpicTask extends Task {

    private LocalDateTime endTime;
    private final HashMap<Integer, TaskStatus> subTasks = new HashMap<>();
    private final TreeMap<LocalDateTime, Integer> subTasksStartTime = new TreeMap<>((Comparator.naturalOrder()));
    private final HashMap<Integer, Long> subTasksDuration = new HashMap<>();

    /**
     * Конструктор для создания новых эпиков.
     * Присваивается новый порядковый номер
     *
     * @param task - объект класса NewTask для создания новых задач
     */
    public EpicTask(NewTask task) {
        super(task);
        this.startTime = null;
        this.duration = 0;
        this.endTime = null;
        updateStatus();
    }

    /**
     * Конструктор для обновления эпика
     * Номер остается прежним и указывается при обновлении
     */
    public EpicTask(Task epic) {
        super(epic);
        updateStatus();
        setEpicTimeAndDuration();
    }

    public EpicTask(String taskTitle, String taskDescription, TaskStatus taskStatus, int taskIdNumber, LocalDateTime startTime, long duration) {
        super(taskTitle, taskDescription, taskStatus, taskIdNumber, startTime, duration);
    }

    public void addSubTask(int subTaskId, TaskStatus status, LocalDateTime startTime, long duration) {
        subTasks.put(subTaskId, status);
        subTasksStartTime.put(startTime, subTaskId);
        subTasksDuration.put(subTaskId, duration);
        updateStatus();
        setEpicTimeAndDuration();
    }

    public void removeSubTask(int subTaskId) {
        subTasks.remove(subTaskId);
        subTasksDuration.remove(subTaskId);
        for (LocalDateTime time : subTasksStartTime.keySet()) {
            if (subTasksStartTime.get(time) == subTaskId)
                subTasksStartTime.remove(time);
        }
        updateStatus();
        setEpicTimeAndDuration();
    }

    /**
     * Возвращает лист с подзадачами входящими в эпик
     * @return - копия листа с подзадачами
     */
    public List<Integer> getSubTasksIds() {
        return new ArrayList<>(subTasks.keySet());
    }

    /**
     * Возвращает лист с уникальными статусами подзадач, входящих в эпик
     * @return - лист с уникальными статусами подзадач
     */
    private LinkedHashSet<TaskStatus> getSubTasksStatuses() {
        return new LinkedHashSet<>(subTasks.values());
    }

    /**
     * определяет статус эпика через проверку статусов подзадач, хранящихся в TaskManager
     */
    private void updateStatus() {
        if (getSubTasksIds().isEmpty()) {
            setTaskStatus(TaskStatus.NEW);
        } else {

            if (getSubTasksStatuses().size() == 1) {
                for (TaskStatus status : getSubTasksStatuses()) setTaskStatus(status);
            } else setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }

    private void setEpicTimeAndDuration() {
        startTime = calculateStarTime();
        duration = calculateDuration();
        endTime = calculateEndTime();
    }

    @Override
    protected LocalDateTime calculateEndTime() {
        if (subTasksDuration != null && subTasksDuration.isEmpty()) return null;
        return subTasksStartTime.lastKey().plusMinutes(subTasksDuration.get(subTasksStartTime.get(subTasksStartTime.lastKey())));
    }

    private LocalDateTime calculateStarTime() {
        if (subTasksStartTime != null && subTasksStartTime.isEmpty()) return null;
        return subTasksStartTime.firstKey();
    }

    private long calculateDuration() {
        if (calculateStarTime() == null || calculateEndTime() == null) return 0;
        return Duration.between(calculateStarTime(), calculateEndTime()).toMinutes();
    }

    @Override
    public String toString() {
        String result = "\n№" + getTaskIdNumber() + ". Эпик" +
                ". Название задачи - " + getTaskTitle() +
                ". Описание задачи: " + getTaskDescription() +
                ". Статус задачи: " + getTaskStatus() +
                ". Время начала: " + getStartTime() +
                ". Продолжительность: " + getDuration() +
                ". Время окончания: " + calculateEndTime();
        if (subTasks.isEmpty()) {
            result = result + ". Подзадачи отсутствуют.";
        } else {
            result = result + ". Эпик содержит следующие подзадачи: \n" + Arrays.toString(getSubTasksIds().toArray());
        }
        return  result;
    }
}