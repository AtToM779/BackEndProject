package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String email;
    private String matricula;
    private String senha;
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String nome, String email, String matricula, String senha) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.senha = senha;
        this.disciplinasMatriculadas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinasMatriculadas.add(disciplina);
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }
}