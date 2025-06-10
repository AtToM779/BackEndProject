package org.example.gui;

import org.example.model.Aluno;
import org.example.model.Disciplina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaNotasHistorico extends JFrame {
    public TelaNotasHistorico(Aluno aluno) {
        super("Notas e Histórico - " + aluno.getMatricula());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] cols = {"Código", "Disciplina", "Nota"};
        DefaultTableModel tabelaModel = new DefaultTableModel(cols, 0);

        for (Disciplina d : aluno.getDisciplinasMatriculadas()) {
            Double nota = d.getNota(aluno.getMatricula());
            tabelaModel.addRow(new Object[]{
                    d.getCodigo(),
                    d.getNome(),
                    nota != null ? nota : "N/A"
            });
        }

        JTable tabela = new JTable(tabelaModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }
}
