package by_.gsu.epamlab.todoapp.entities;

import java.io.Serializable;

/**
 * Represents subtask.
 */
public class SubTask implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int taskId;
    private String text;
    private boolean done;

    public SubTask() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
