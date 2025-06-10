package org.example.gui;

import org.example.model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TelaFinanceiro extends JFrame {
    private Aluno aluno;

    public TelaFinanceiro(Aluno aluno) {
        super("Financeiro - " + aluno.getNome());
        this.aluno = aluno;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Status Financeiro", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel de resumo
        JPanel panelResumo = new JPanel(new GridLayout(1, 2, 20, 10));
        panelResumo.setBorder(BorderFactory.createTitledBorder("Resumo"));

        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        // Por exemplo, custo de R$50 por crédito
        double totalPago = aluno.getDisciplinasMatriculadas().stream()
                .mapToInt(d -> d.getCreditos() * 50)
                .sum();
        String proximoVenc = LocalDate.now().plusDays(15)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        JLabel lblTotalPago = new JLabel("Total Pago: " + fmt.format(totalPago));
        lblTotalPago.setFont(lblTotalPago.getFont().deriveFont(14f));
        JLabel lblProximo = new JLabel("Próximo Vencimento: " + proximoVenc);
        lblProximo.setFont(lblProximo.getFont().deriveFont(14f));

        panelResumo.add(lblTotalPago);
        panelResumo.add(lblProximo);

        // Tabela de transações
        String[] cols = {"Data", "Descrição", "Valor", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
        tableModel.addRow(new Object[]{"01/05/2025", "Mensalidade Maio", fmt.format(200.00), "Pago"});
        tableModel.addRow(new Object[]{"01/06/2025", "Mensalidade Junho", fmt.format(200.00), "Pendente"});

        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Histórico de Pagamentos"));

        // Conteúdo central
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.add(panelResumo);
        panelCenter.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCenter.add(scroll);
        add(panelCenter, BorderLayout.CENTER);

        // Botão fechar
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel panelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelRodape.add(btnFechar);
        add(panelRodape, BorderLayout.SOUTH);
    }
}
