package org.example.gui;

import org.example.model.Aluno;

import javax.swing.*;
import java.awt.*;

public class TelaSuporte extends JFrame {
    private JTextField txtAssunto;
    private JTextArea txtMensagem;
    private JButton btnEnviar, btnFechar;

    public TelaSuporte(Aluno aluno) {
        super("Suporte / Contato - " + aluno.getNome());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Fale Conosco", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 20f));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Assunto:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtAssunto = new JTextField();
        formPanel.add(txtAssunto, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Mensagem:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.weighty = 1.0;
        txtMensagem = new JTextArea(8, 40);
        txtMensagem.setLineWrap(true);
        txtMensagem.setWrapStyleWord(true);
        JScrollPane scrollMsg = new JScrollPane(txtMensagem);
        formPanel.add(scrollMsg, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnEnviar = new JButton("Enviar");
        btnFechar = new JButton("Fechar");

        btnEnviar.addActionListener(e -> {
            String assunto = txtAssunto.getText().trim();
            String msg = txtMensagem.getText().trim();
            if (assunto.isEmpty() || msg.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, preencha assunto e mensagem.",
                        "Campos obrigatórios",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Aqui você poderia chamar um serviço de envio de e-mail / API
            JOptionPane.showMessageDialog(this,
                    "Mensagem enviada com sucesso!\nAssunto: " + assunto,
                    "Enviado", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        btnFechar.addActionListener(e -> dispose());
        btnPanel.add(btnEnviar);
        btnPanel.add(btnFechar);

        add(btnPanel, BorderLayout.SOUTH);
    }
}
