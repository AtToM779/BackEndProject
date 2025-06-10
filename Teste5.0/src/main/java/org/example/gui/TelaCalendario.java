package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCalendario extends JFrame {
    public TelaCalendario() {
        super("Calendário Acadêmico");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JLabel lblMes = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM yyyy")), SwingConstants.CENTER);
        lblMes.setFont(lblMes.getFont().deriveFont(Font.BOLD, 16f));
        add(lblMes, BorderLayout.NORTH);

        JTextArea txtCalendario = new JTextArea(
                "01/07 - Início de aulas\n" +
                        "15/07 - Recesso\n" +
                        "30/07 - Fim do semestre\n"
        );
        txtCalendario.setEditable(false);
        add(new JScrollPane(txtCalendario), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(btnFechar);
        add(rodape, BorderLayout.SOUTH);
    }
}