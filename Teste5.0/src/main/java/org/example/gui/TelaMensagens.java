package org.example.gui;

import org.example.model.Aluno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TelaMensagens extends JFrame {
    private Aluno aluno;
    private DefaultListModel<Mensagem> modelMensagens;
    private JList<Mensagem> listMensagens;
    private JTextArea txtEscrever;
    private JButton btnEnviar;

    public TelaMensagens(Aluno aluno) {
        super("Mensagens / Comunicados - " + aluno.getNome());
        this.aluno = aluno;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(new EmptyBorder(15,15,15,15));
        add(panel);

        // Título
        JLabel lblTitulo = new JLabel("Caixa de Mensagens", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Lista de mensagens
        modelMensagens = new DefaultListModel<>();
        carregarMensagens();
        listMensagens = new JList<>(modelMensagens);
        listMensagens.setCellRenderer(new MensagemRenderer());
        JScrollPane scrollList = new JScrollPane(listMensagens);
        scrollList.setBorder(BorderFactory.createTitledBorder("Recebidas"));
        panel.add(scrollList, BorderLayout.CENTER);

        // Painel de envio de mensagem
        JPanel envioPanel = new JPanel(new BorderLayout(5,5));
        envioPanel.setBorder(BorderFactory.createTitledBorder("Escrever Mensagem"));

        txtEscrever = new JTextArea(4, 40);
        txtEscrever.setLineWrap(true);
        txtEscrever.setWrapStyleWord(true);
        envioPanel.add(new JScrollPane(txtEscrever), BorderLayout.CENTER);

        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> enviarMensagem());
        envioPanel.add(btnEnviar, BorderLayout.EAST);

        panel.add(envioPanel, BorderLayout.SOUTH);
    }

    private void carregarMensagens() {
        // Exemplo de mensagens; futuramente carregar de repositório
        List<Mensagem> msgs = new ArrayList<>();
        msgs.add(new Mensagem("Admin", "Bem-vindo ao sistema!", LocalDateTime.now().minusDays(2)));
        msgs.add(new Mensagem("Coordenação", "Lembrete: matrícula até 30/06.", LocalDateTime.now().minusHours(5)));
        modelMensagens.clear();
        msgs.forEach(modelMensagens::addElement);
    }

    private void enviarMensagem() {
        String texto = txtEscrever.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite uma mensagem antes de enviar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Mensagem m = new Mensagem(aluno.getNome(), texto, LocalDateTime.now());
        modelMensagens.addElement(m);
        txtEscrever.setText("");
        JOptionPane.showMessageDialog(this, "Mensagem enviada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    // Classe interna para representar uma mensagem
    private static class Mensagem {
        String remetente;
        String conteudo;
        LocalDateTime timestamp;
        Mensagem(String r, String c, LocalDateTime t) { this.remetente = r; this.conteudo = c; this.timestamp = t; }
        @Override public String toString() { return remetente + ": " + conteudo; }
    }

    // Renderer customizado para exibir remetente, conteúdo e data
    private static class MensagemRenderer extends JTextArea implements ListCellRenderer<Mensagem> {
        public MensagemRenderer() {
            setOpaque(true);
            setLineWrap(true);
            setWrapStyleWord(true);
            setBorder(new EmptyBorder(5,5,5,5));
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends Mensagem> list,
                                                      Mensagem value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            String data = value.timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            setText("De: " + value.remetente + " - " + data + "\n" + value.conteudo);
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            if (isSelected) {
                setBackground(new Color(220, 235, 255));
            } else {
                setBackground(Color.WHITE);
            }
            return this;
        }
    }
}
