package org.example.gui;

import org.example.model.Professor;
import org.example.model.Disciplina;
import org.example.repository.RepositorioDisciplinas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaProfessor extends JFrame {
    private Professor professor;
    private JComboBox<Disciplina> comboDisciplinas;
    private DefaultListModel<String> modelAlunos;
    private JList<String> listAlunos;
    private JTextField txtMatricula, txtNota;

    public TelaProfessor(Professor professor) {
        this.professor = professor;
        setTitle("Tela do Professor - " + professor.getNome());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + professor.getNome() + "!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(lblBemVindo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(10, 10));

        comboDisciplinas = new JComboBox<>();
        List<Disciplina> todas = RepositorioDisciplinas.getInstance().getTodasDisciplinas();
        todas.forEach(comboDisciplinas::addItem);
        comboDisciplinas.addActionListener(e -> atualizarListaAlunos());
        centro.add(comboDisciplinas, BorderLayout.NORTH);

        modelAlunos = new DefaultListModel<>();
        listAlunos = new JList<>(modelAlunos);
        JScrollPane scrollAlunos = new JScrollPane(listAlunos);
        scrollAlunos.setBorder(BorderFactory.createTitledBorder("Alunos Matriculados"));
        centro.add(scrollAlunos, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        JPanel sul = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0;
        sul.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        txtMatricula = new JTextField(10);
        sul.add(txtMatricula, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        sul.add(new JLabel("Nota:"), gbc);
        gbc.gridx = 1;
        txtNota = new JTextField(5);
        sul.add(txtNota, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        JButton btnAtribuir = new JButton("Atribuir Nota");
        btnAtribuir.addActionListener(e -> atribuirNota());
        sul.add(btnAtribuir, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
        sul.add(btnLogout, gbc);

        add(sul, BorderLayout.SOUTH);

        // Popula inicialmente
        atualizarListaAlunos();
    }

    private void atualizarListaAlunos() {
        modelAlunos.clear();
        Disciplina d = (Disciplina) comboDisciplinas.getSelectedItem();
        if (d != null) {
            // Agora traz **todas** as matrículas, mesmo sem nota
            for (String mat : d.getMatriculas()) {
                Double nota = d.getNota(mat);
                String texto = mat + " → " + (nota != null ? nota : "Sem nota");
                modelAlunos.addElement(texto);
            }
        }
    }



    private void atribuirNota() {
        Disciplina d = (Disciplina) comboDisciplinas.getSelectedItem();
        String mat = txtMatricula.getText().trim();
        try {
            double nota = Double.parseDouble(txtNota.getText().trim());
            d.atribuirNota(mat, nota);
            JOptionPane.showMessageDialog(this, "Nota atribuída com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            atualizarListaAlunos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Insira uma nota válida.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
