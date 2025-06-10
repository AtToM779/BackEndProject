// src/main/java/org/example/repository/RepositorioDisciplinas.java
package org.example.repository;

import org.example.model.Disciplina;
import java.util.*;

public class RepositorioDisciplinas {
    private static RepositorioDisciplinas instance;
    private Map<String, Disciplina> disciplinas;

    private RepositorioDisciplinas() {
        disciplinas = new LinkedHashMap<>();
        // ------- LISTA AMPLIADA DE DISCIPLINAS -------
        disciplinas.put("POO101",  new Disciplina("POO101",  "Programação Orientada a Objetos",      5));
        disciplinas.put("BD102",   new Disciplina("BD102",   "Banco de Dados",                        5));
        disciplinas.put("SO103",   new Disciplina("SO103",   "Sistemas Operacionais",                 5));
        disciplinas.put("ALG104",  new Disciplina("ALG104",  "Algoritmos e Estruturas de Dados",      5));
        disciplinas.put("MD105",   new Disciplina("MD105",   "Matemática Discreta",                   5));
        disciplinas.put("RED106",  new Disciplina("RED106",  "Redes de Computadores",                 5));
        disciplinas.put("IA107",   new Disciplina("IA107",   "Introdução à Inteligência Artificial",  5));
        disciplinas.put("WEB108",  new Disciplina("WEB108",  "Desenvolvimento Web",                   5));
        disciplinas.put("ENG109",  new Disciplina("ENG109",  "Inglês Técnico",                        5));
        disciplinas.put("BD110",   new Disciplina("BD110",   "NoSQL e Big Data",                      5));
        disciplinas.put("ARQ111",  new Disciplina("ARQ111",  "Arquitetura de Computadores",           5));
        disciplinas.put("SEC112",  new Disciplina("SEC112",  "Segurança da Informação",               5));
        // ---------------------------------------------
    }

    public static synchronized RepositorioDisciplinas getInstance() {
        if (instance == null) {
            instance = new RepositorioDisciplinas();
        }
        return instance;
    }

    public List<Disciplina> getTodasDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }

    public Disciplina getPorCodigo(String codigo) {
        return disciplinas.get(codigo);
    }
}
