package org.example.gui;

import org.example.model.Aluno;
import org.example.model.Disciplina;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TelaDocumentos extends JFrame {
    private Aluno aluno;

    public TelaDocumentos(Aluno aluno) {
        super("Documentos - " + aluno.getMatricula());
        this.aluno = aluno;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JLabel lblTitulo = new JLabel("Documentos Disponíveis", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        add(lblTitulo, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Comprovante de Matrícula");
        JList<String> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(list), BorderLayout.CENTER);

        JButton btnBaixar = new JButton("Baixar");
        btnBaixar.addActionListener(e -> {
            String sel = list.getSelectedValue();
            if ("Comprovante de Matrícula".equals(sel)) {
                try {
                    gerarComprovante();
                    JOptionPane.showMessageDialog(this,
                            "Comprovante gerado em: comprovante_" + aluno.getMatricula() + ".txt",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao gerar comprovante: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(btnBaixar);
        rodape.add(btnFechar);
        add(rodape, BorderLayout.SOUTH);
    }

    private void gerarComprovante() throws IOException {
        String filename = "comprovante_" + aluno.getMatricula() + ".txt";
        List<Disciplina> disc = aluno.getDisciplinasMatriculadas();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Comprovante de Matrícula");
            writer.println("Aluno: " + aluno.getNome());
            writer.println("Matrícula: " + aluno.getMatricula());
            writer.println();
            writer.println("Disciplinas Matriculadas:");
            for (Disciplina d : disc) {
                writer.printf("- %s (%d créditos)%n", d.getNome(), d.getCreditos());
            }
        }
    }
}
