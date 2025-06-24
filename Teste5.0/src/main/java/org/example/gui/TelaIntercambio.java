package org.example.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaIntercambio extends JFrame {
    private DefaultListModel<Programa> model;
    private JList<Programa> list;
    private JTextField txtFiltro;

    public TelaIntercambio() {
        super("Programa de Intercâmbio");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(panel);

        JLabel lblTitle = new JLabel("Oportunidades de Intercâmbio", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(lblTitle, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        txtFiltro = new JTextField();
        txtFiltro.setToolTipText("Filtrar por país ou universidade...");
        JButton btnFilter = new JButton("Filtrar");
        btnFilter.addActionListener(e -> filtrar());
        searchPanel.add(txtFiltro, BorderLayout.CENTER);
        searchPanel.add(btnFilter, BorderLayout.EAST);
        panel.add(searchPanel, BorderLayout.BEFORE_FIRST_LINE);

        model = new DefaultListModel<>();
        carregarProgramas();
        list = new JList<>(model);
        list.setCellRenderer(new ProgramaRenderer());
        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createTitledBorder("Programas Disponíveis"));
        panel.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnJoin = new JButton("Inscrever-se");
        btnJoin.addActionListener(e -> inscrever());
        JButton btnClose = new JButton("Fechar");
        btnClose.addActionListener(e -> dispose());
        footer.add(btnJoin);
        footer.add(btnClose);
        panel.add(footer, BorderLayout.SOUTH);
    }

    private void carregarProgramas() {
        List<Programa> listProg = new ArrayList<>();
        listProg.add(new Programa("Universidade X", "EUA", "Jan/2026"));
        listProg.add(new Programa("Universidade Y", "Canadá", "Set/2025"));
        listProg.add(new Programa("Universidade Z", "Alemanha", "Mar/2026"));
        model.clear();
        listProg.forEach(model::addElement);
    }

    private void filtrar() {
        String termo = txtFiltro.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            carregarProgramas();
            return;
        }
        DefaultListModel<Programa> filtered = new DefaultListModel<>();
        for (int i = 0; i < model.size(); i++) {
            Programa p = model.getElementAt(i);
            if (p.universidade.toLowerCase().contains(termo) || p.pais.toLowerCase().contains(termo)) {
                filtered.addElement(p);
            }
        }
        list.setModel(filtered);
    }

    private void inscrever() {
        Programa p = list.getSelectedValue();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Selecione um programa.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Inscrição realizada em: " + p.universidade + " (" + p.pais + ") em " + p.periodo,
                "Inscrito", JOptionPane.INFORMATION_MESSAGE);
    }

    private static class Programa {
        String universidade;
        String pais;
        String periodo;
        Programa(String u, String p, String per) { universidade = u; pais = p; periodo = per; }
        @Override public String toString() { return universidade + " - " + pais + " (" + periodo + ")"; }
    }

    private static class ProgramaRenderer extends JLabel implements ListCellRenderer<Programa> {
        public ProgramaRenderer() { setOpaque(true); setBorder(new EmptyBorder(5,5,5,5)); }
        @Override
        public Component getListCellRendererComponent(JList<? extends Programa> list,
                                                      Programa value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText("🎓 " + value.toString());
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            if (isSelected) {
                setBackground(new Color(200, 230, 255));
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.DARK_GRAY);
            }
            return this;
        }
    }
}
