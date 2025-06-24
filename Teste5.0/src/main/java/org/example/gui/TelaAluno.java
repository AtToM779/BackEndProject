package org.example.gui;

import org.example.model.Aluno;
import javax.swing.*;
import java.awt.*;

public class TelaAluno extends JFrame {
    private Aluno aluno;

    public TelaAluno(Aluno aluno) {
        super("Menu do Aluno – " + aluno.getNome());
        this.aluno = aluno;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + aluno.getNome() + "!", SwingConstants.CENTER);
        lblBemVindo.setFont(lblBemVindo.getFont().deriveFont(Font.BOLD, 20f));
        add(lblBemVindo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(4, 3, 15, 15));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnDisciplinas   = new JButton("Minhas Disciplinas");
        JButton btnNotas         = new JButton("Notas e Histórico");
        JButton btnMatricula     = new JButton("Matrícula");
        JButton btnCalendario    = new JButton("Calendário Acadêmico");
        JButton btnMensagens     = new JButton("Mensagens / Comunicados");
        JButton btnDocumentos    = new JButton("Documentos");
        JButton btnFinanceiro    = new JButton("Financeiro");
        JButton btnSuporte       = new JButton("Suporte / Contato");
        JButton btnExtensao      = new JButton("Cursos de Extensão");
        JButton btnIntercambio   = new JButton("Intercâmbio");
        JButton btnEventos       = new JButton("Eventos");
        JButton btnLogout        = new JButton("Logout");

        painelBotoes.add(btnDisciplinas);
        painelBotoes.add(btnNotas);
        painelBotoes.add(btnMatricula);
        painelBotoes.add(btnCalendario);
        painelBotoes.add(btnMensagens);
        painelBotoes.add(btnDocumentos);
        painelBotoes.add(btnFinanceiro);
        painelBotoes.add(btnSuporte);
        painelBotoes.add(btnExtensao);
        painelBotoes.add(btnIntercambio);
        painelBotoes.add(btnEventos);
        painelBotoes.add(btnLogout);

        add(painelBotoes, BorderLayout.CENTER);

 
        JTextArea areaNotificacoes = new JTextArea(
                "Notificações:\n" +
                        "- Matrícula aberta até 30/06\n" +
                        "- Palestra em 20/07\n" +
                        "- Boletim disponível em 05/08"
        );
        areaNotificacoes.setEditable(false);
        areaNotificacoes.setBorder(BorderFactory.createTitledBorder("Notificações"));
        add(areaNotificacoes, BorderLayout.EAST);

        btnDisciplinas.addActionListener(e -> new TelaMinhasDisciplinas(aluno).setVisible(true));
        btnNotas.addActionListener(e    -> new TelaNotasHistorico(aluno).setVisible(true));
        btnMatricula.addActionListener(e-> new TelaMatricula(aluno).setVisible(true));
        btnCalendario.addActionListener(e-> new TelaCalendario().setVisible(true));
        btnMensagens.addActionListener(e -> new TelaMensagens(aluno).setVisible(true));
        btnDocumentos.addActionListener(e-> new TelaDocumentos(aluno).setVisible(true));
        btnFinanceiro.addActionListener(e-> new TelaFinanceiro(aluno).setVisible(true));
        btnSuporte.addActionListener(e   -> new TelaSuporte(aluno).setVisible(true));
        btnExtensao.addActionListener(e  -> new TelaCursosExtensao().setVisible(true));
        btnIntercambio.addActionListener(e-> new TelaIntercambio().setVisible(true));
        btnEventos.addActionListener(e    -> new TelaEventos().setVisible(true));
        btnLogout.addActionListener(e     -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
    }
}
