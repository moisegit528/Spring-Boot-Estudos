package com.moisegit.todolist.task;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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

        // validação de data/hora
        var currentDate = LocalDateTime.now();  // data atual
        if (currentDate.isAfter(taskModel.getStartTime()) || currentDate.isAfter(taskModel.getEndTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deve inserir uma data/hora de ínicio e término, maior que a atual. ");
        }

        if (taskModel.getStartTime().isAfter(taskModel.getEndTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    // funcionalidade para usuário listar todas as tarefas que possui com base na sua credencial
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByidUser((UUID) idUser);
        return tasks;
    }
    // funcionalidade para usuário fazer update/atualização da tarefa se necessário.
    @PutMapping("/{id}")
    public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
        var idUser = request.getAttribute("idUser"); // variável criada para guardar idUser do usuário.
        taskModel.setIdUser((UUID) idUser); // garante que a tarefa atualizada, continue vinculado ao usuário autenticado
        taskModel.setId(id); // mantém o ID "identificador" existente do banco de dados, garantindo update e não uma nova tarefa.
        return this.taskRepository.save(taskModel); // salve no banco de dados e devolva ao cliente atualizado.
    }
}

