package org.example.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TelaEventos extends JFrame {
    private DefaultListModel<Evento> model;
    private JList<Evento> list;
    private JTextField txtFiltro;

    public TelaEventos() {
        super("Eventos Acadêmicos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(panel);

        JLabel lblTitle = new JLabel("Próximos Eventos Acadêmicos", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(lblTitle, BorderLayout.NORTH);

        // Filtro por data ou nome
        JPanel filterPanel = new JPanel(new BorderLayout(5, 5));
        txtFiltro = new JTextField();
        txtFiltro.setToolTipText("Filtrar por nome ou data (dd/MM)...");
        JButton btnFiltro = new JButton("Filtrar");
        btnFiltro.addActionListener(e -> aplicarFiltro());
        filterPanel.add(txtFiltro, BorderLayout.CENTER);
        filterPanel.add(btnFiltro, BorderLayout.EAST);
        panel.add(filterPanel, BorderLayout.BEFORE_FIRST_LINE);

        // Lista de eventos
        model = new DefaultListModel<>();
        carregarEventos();
        list = new JList<>(model);
        list.setCellRenderer(new EventoRenderer());
        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createTitledBorder("Selecione um evento"));
        panel.add(scroll, BorderLayout.CENTER);

        // Rodapé com botão detalhes e fechar
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnDetalhes = new JButton("Ver Detalhes");
        btnDetalhes.addActionListener(e -> mostrarDetalhes());
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        footer.add(btnDetalhes);
        footer.add(btnFechar);
        panel.add(footer, BorderLayout.SOUTH);
    }

    private void carregarEventos() {
        List<Evento> lista = new ArrayList<>();
        lista.add(new Evento("Semana de Tecnologia", LocalDate.of(2025, 7, 10)));
        lista.add(new Evento("Palestra de Carreiras", LocalDate.of(2025, 7, 20)));
        lista.add(new Evento("Simulado ENADE", LocalDate.of(2025, 8, 1)));
        lista.add(new Evento("Hackathon Interno", LocalDate.of(2025, 9, 5)));
        model.clear();
        lista.forEach(model::addElement);
    }

    private void aplicarFiltro() {
        String termo = txtFiltro.getText().trim().toLowerCase();
        DefaultListModel<Evento> filtered = new DefaultListModel<>();
        for (int i = 0; i < model.size(); i++) {
            Evento ev = model.get(i);
            String data = ev.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (ev.getNome().toLowerCase().contains(termo) || data.contains(termo)) {
                filtered.addElement(ev);
            }
        }
        list.setModel(filtered);
    }

    private void mostrarDetalhes() {
        Evento ev = list.getSelectedValue();
        if (ev == null) {
            JOptionPane.showMessageDialog(this, "Selecione um evento.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String msg = String.format(
                "Evento: %s%nData: %s%n%nParticipe e não perca!",
                ev.getNome(),
                ev.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
        JOptionPane.showMessageDialog(this, msg, "Detalhes do Evento", JOptionPane.INFORMATION_MESSAGE);
    }

    private static class Evento {
        private String nome;
        private LocalDate data;
        public Evento(String nome, LocalDate data) { this.nome = nome; this.data = data; }
        public String getNome() { return nome; }
        public LocalDate getData() { return data; }
        @Override public String toString() {
            return nome + " (" + data.format(DateTimeFormatter.ofPattern("dd/MM")) + ")";
        }
    }

    private static class EventoRenderer extends JLabel implements ListCellRenderer<Evento> {
        public EventoRenderer() {
            setOpaque(true);
            setBorder(new EmptyBorder(5,5,5,5));
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends Evento> list,
                                                      Evento value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText("📅 " + value.toString());
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            if (isSelected) {
                setBackground(new Color(235, 245, 255));
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.DARK_GRAY);
            }
            return this;
        }
    }
}
