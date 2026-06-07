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
@Entity // define a classe como uma entidade.
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
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String priority;

    private UUID idUser; // estou gerando um ID para o usuário

    @CreationTimestamp // Mostra DATA/HORA da criação de cada novo registro no BANCO DE DADOS.
    private LocalDateTime createdAt;
}
