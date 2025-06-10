// src/main/java/org/example/model/Disciplina.java
package org.example.model;

import java.util.*;

public class Disciplina {
    private String codigo;
    private String nome;
    private int creditos;
    private Map<String, Double> notasAlunos = new HashMap<>();
    private Set<String> matriculas = new LinkedHashSet<>(); // nova

    public Disciplina(String codigo, String nome, int creditos) {
        this.codigo = codigo;
        this.nome = nome;
        this.creditos = creditos;
    }

    public String getCodigo() { return codigo; }
    public String getNome()   { return nome; }
    public int getCreditos()  { return creditos; }

    // matrícula: aluno escolheu a disciplina
    public void matricularAluno(String matricula) {
        matriculas.add(matricula);
    }
    public Set<String> getMatriculas() {
        return Collections.unmodifiableSet(matriculas);
    }

    // nota: professor preenche
    public void atribuirNota(String matricula, double nota) {
        notasAlunos.put(matricula, nota);
    }
    public Double getNota(String matricula) {
        return notasAlunos.get(matricula);
    }

    @Override
    public String toString() {
        return codigo + " – " + nome;
    }
}
