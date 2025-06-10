package org.example.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaCursosExtensao extends JFrame {
    private DefaultListModel<String> modelCursos;
    private JList<String> listCursos;
    private JTextField txtBusca;

    public TelaCursosExtensao() {
        super("Cursos de Extensão");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        // Painel principal com borda
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(panel);

        // Título
        JLabel lblTitulo = new JLabel("Cursos de Extensão Disponíveis", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Campo de busca
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        txtBusca = new JTextField();
        txtBusca.setToolTipText("Buscar curso...");
        JButton btnBusca = new JButton("Buscar");
        btnBusca.addActionListener(e -> filtrarCursos());
        searchPanel.add(txtBusca, BorderLayout.CENTER);
        searchPanel.add(btnBusca, BorderLayout.EAST);
        panel.add(searchPanel, BorderLayout.BEFORE_FIRST_LINE);

        // Lista de cursos com renderer customizado
        modelCursos = new DefaultListModel<>();
        carregarCursos();
        listCursos = new JList<>(modelCursos);
        listCursos.setCellRenderer(new CursoCellRenderer());
        JScrollPane scroll = new JScrollPane(listCursos);
        scroll.setBorder(BorderFactory.createTitledBorder("Selecione um curso"));
        panel.add(scroll, BorderLayout.CENTER);

        // Rodapé com botão fechar
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        footer.add(btnFechar);
        panel.add(footer, BorderLayout.SOUTH);
    }

    private void carregarCursos() {
        String[] cursos = {
                "Introdução à Programação",
                "Design Thinking",
                "Gestão de Projetos",
                "Inglês Técnico",
                "Marketing Digital",
                "Data Science Básico",
                "UX/UI Design",
                "Finanças Pessoais",
                "Comunicação Corporativa",
                "Empreendedorismo"
        };
        modelCursos.clear();
        for (String c : cursos) modelCursos.addElement(c);
    }

    private void filtrarCursos() {
        String termo = txtBusca.getText().trim().toLowerCase();
        carregarCursos();
        if (!termo.isEmpty()) {
            for (int i = modelCursos.size() - 1; i >= 0; i--) {
                if (!modelCursos.get(i).toLowerCase().contains(termo)) {
                    modelCursos.remove(i);
                }
            }
        }
    }

    // Renderer para adicionar ícone e estilo às linhas
    private static class CursoCellRenderer extends JLabel implements ListCellRenderer<String> {
        public CursoCellRenderer() {
            setOpaque(true);
            setBorder(new EmptyBorder(5, 5, 5, 5));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list,
                                                      String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText("📚 " + value);
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            if (isSelected) {
                setBackground(new Color(220, 235, 255));
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.DARK_GRAY);
            }
            return this;
        }
    }
}
