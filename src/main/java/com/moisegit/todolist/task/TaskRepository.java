package com.moisegit.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByidUser(UUID idUser); // lista do taskModel, baseado no usuário "idUser"
    TaskModel findByIdAndIdUser(UUID id, UUID idUser);
}
