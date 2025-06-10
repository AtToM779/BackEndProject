package org.example.service;

import org.example.model.Disciplina;
import org.example.model.Aluno;
import org.example.repository.RepositorioDisciplinas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BoletimGenerator {
    public static void gerarBoletim(Aluno aluno) throws IOException {
        List<Disciplina> disciplinas = aluno.getDisciplinasMatriculadas();
        try (PrintWriter writer = new PrintWriter(new FileWriter("boletim_" + aluno.getMatricula() + ".txt"))) {
            writer.println("Boletim do Aluno: " + aluno.getNome());
            writer.println("Matrícula: " + aluno.getMatricula());
            writer.println();
            writer.println("Disciplinas Cursadas:");
            writer.println();

            if (disciplinas.isEmpty()) {
                writer.println("- Nenhuma disciplina matriculada");
            } else {
                for (Disciplina d : disciplinas) {
                    Double nota = d.getNota(aluno.getMatricula());
                    writer.printf("%-30s %s%n", d.getNome(), nota != null ? nota : "N/A");
                }
            }
        }
    }
}