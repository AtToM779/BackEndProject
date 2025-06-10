package org.example.service;

import org.example.model.Disciplina;
import org.example.model.Aluno;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HistoricoGenerator {
    public static void gerarHistorico(Aluno aluno) throws IOException {
        List<Disciplina> disciplinas = aluno.getDisciplinasMatriculadas();
        try (PrintWriter writer = new PrintWriter(new FileWriter("historico_" + aluno.getMatricula() + ".txt"))) {
            writer.println("Histórico do Aluno: " + aluno.getNome());
            writer.println("Matrícula: " + aluno.getMatricula());
            writer.println();
            writer.println("Histórico de Disciplinas:");
            writer.println();

            if (disciplinas.isEmpty()) {
                writer.println("- Nenhuma disciplina registrada");
            } else {
                for (Disciplina d : disciplinas) {
                    Double nota = d.getNota(aluno.getMatricula());
                    String status = nota != null ? "Concluída (" + nota + ")" : "Em andamento";
                    writer.printf("- %s (%d créditos) → %s%n", d.getNome(), d.getCreditos(), status);
                }
            }
        }
    }
}
