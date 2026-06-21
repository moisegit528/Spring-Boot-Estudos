package com.moisegit.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Data // utilizado para ter getters, setters, ToString, equals, hash e construtor básico.
@Entity(name = "tb_tasks") // Define a classe como uma entidade. Name - define o nome da tabela no Banco de Dados
public class TaskModel {
    /**
     * ID
     * Usuário (ID_USUARIO)
     * Descrição
     * Título
     * Data de início
     * Data de término
     * Prioridade
     * */
    @Id // utilizado para criar chave-primária
    @GeneratedValue(generator = "UUID") // estou pedindo para gerar IDs automáticos de forma única.
    private UUID id; // estou definindo como chave-primária.
    private String description;

    @Column(length = 50) // limitando a 50 caracteres
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String priority;

    private UUID idUser; // estou gerando um ID para o usuário

    @CreationTimestamp // Mostra DATA/HORA da criação de cada novo registro no BANCO DE DADOS.
    private LocalDateTime createdAt;

    // tratar erro
    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("Campo title deve conter 50 caracteres no máximo.");
        }
        this.title = title;
    }

}
