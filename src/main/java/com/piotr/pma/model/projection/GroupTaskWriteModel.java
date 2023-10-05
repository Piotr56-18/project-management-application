package com.piotr.pma.model.projection;

import com.piotr.pma.model.Task;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public Task toTask(){
        return new Task(description, deadline);
    }
}
