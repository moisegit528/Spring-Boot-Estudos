package com.moisegit.todolist.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Usado para criar APIs - Retorna dados direto no corpo da resposta (json/xml)
@RequestMapping("/tasks") // Responsável por informar a estrutura da rota http
public class ITaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel) { // TaskModel(entidade), create(função), taskModel(variável)
       var task =  this.taskRepository.save(taskModel);
       return task;
    }
}
