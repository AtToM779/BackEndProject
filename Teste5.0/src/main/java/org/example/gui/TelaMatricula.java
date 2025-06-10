package org.example.gui;

import org.example.model.Aluno;
import org.example.model.Disciplina;
import org.example.repository.RepositorioDisciplinas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMatricula extends JFrame {
    private Aluno aluno;

    public TelaMatricula(Aluno aluno) {
        super("Matrícula - " + aluno.getNome());
        this.aluno = aluno;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        List<Disciplina> disciplinas = RepositorioDisciplinas.getInstance().getTodasDisciplinas();
        DefaultListModel<Disciplina> model = new DefaultListModel<>();
        for (Disciplina d : disciplinas) model.addElement(d);
        JList<Disciplina> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton btnMatricular = new JButton("Matricular");
        btnMatricular.addActionListener(e -> {
            for (Disciplina d : list.getSelectedValuesList()) {
                aluno.adicionarDisciplina(d);
            }
            JOptionPane.showMessageDialog(this, "Matricula(s) efetuada(s) com sucesso!");
        });

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(btnMatricular, BorderLayout.SOUTH);

        add(panel);
    }
}
