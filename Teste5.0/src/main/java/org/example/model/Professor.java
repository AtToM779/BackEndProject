package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String id;
    private String nome;
    private List<Disciplina> disciplinas;

    public Professor(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}