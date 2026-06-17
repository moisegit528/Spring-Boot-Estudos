package com.moisegit.todolist.task;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController // Usado para criar APIs - Retorna dados direto no corpo da resposta (json/xml)
@RequestMapping("/tasks") // Responsável por informar a estrutura da rota http
public class ITaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {// TaskModel(entidade), create(função), taskModel(variável)
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();  // data atual
        if (currentDate.isAfter(taskModel.getStartTime()) || currentDate.isAfter(taskModel.getEndTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deve inserir uma data/hora de ínicio e término, maior que a atual. ");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}

