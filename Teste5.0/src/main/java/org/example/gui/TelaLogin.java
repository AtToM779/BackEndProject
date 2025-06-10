package org.example.gui;

import org.example.model.Aluno;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        super("Login - Sistema Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Escolha o perfil para entrar", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAluno = new JButton("Entrar como Aluno");
        JButton btnProfessor = new JButton("Entrar como Professor");

        btnAluno.addActionListener(e -> {
            // Cria um Aluno de exemplo e abre a TelaAluno (menu principal do aluno)
            Aluno aluno = new Aluno("Fulano", "fulano@example.com", "2025001", "senha");
            new TelaAluno(aluno).setVisible(true);
            dispose();
        });

        btnProfessor.addActionListener(e -> {
            // Cria um Professor de exemplo e abre a TelaProfessor
            new TelaProfessor(new org.example.model.Professor("P001", "Prof. Silva")).setVisible(true);
            dispose();
        });

        pnlBotoes.add(btnAluno);
        pnlBotoes.add(btnProfessor);
        add(pnlBotoes, BorderLayout.CENTER);
    }
}
