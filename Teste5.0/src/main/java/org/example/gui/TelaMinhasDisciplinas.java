package org.example.gui;

import org.example.model.Aluno;
import org.example.model.Disciplina;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMinhasDisciplinas extends JFrame {
    private static final int TOTAL_CREDITOS = 20;

    public TelaMinhasDisciplinas(Aluno aluno) {
        super("Minhas Disciplinas - " + aluno.getMatricula());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        DefaultListModel<String> modelLista = new DefaultListModel<>();
        List<Disciplina> disciplinas = aluno.getDisciplinasMatriculadas();
        for (Disciplina d : disciplinas) {
            modelLista.addElement(d.getCodigo() + " - " + d.getNome() + " (" + d.getCreditos() + " créditos)");
        }
        JList<String> list = new JList<>(modelLista);
        list.setBorder(BorderFactory.createTitledBorder("Disciplinas Matriculadas"));
        add(new JScrollPane(list), BorderLayout.WEST);

        PieChartPanel chart = new PieChartPanel(disciplinas);
        add(chart, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(btnFechar);
        add(rodape, BorderLayout.SOUTH);
    }

    private class PieChartPanel extends JPanel {
        private List<Disciplina> disciplinas;

        public PieChartPanel(List<Disciplina> disciplinas) {
            this.disciplinas = disciplinas;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (disciplinas == null || disciplinas.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g;
            int w = getWidth(), h = getHeight();
            // maior margem para evitar legendas sobrepostas
            int margin = 60;
            int diameter = Math.min(w, h) - margin * 2;
            int x = (w - diameter) / 2;
            int y = (h - diameter) / 2;
            int cx = x + diameter / 2;
            int cy = y + diameter / 2;

            int startAngle = 0;
            for (Disciplina d : disciplinas) {
                int arc = (int) Math.round(d.getCreditos() / (double) TOTAL_CREDITOS * 360);
                Color color = new Color((int)(Math.random()*0xFFFFFF));
                g2.setColor(color);
                g2.fillArc(x, y, diameter, diameter, startAngle, arc);

                double mid = Math.toRadians(startAngle + arc / 2.0);
                int labelRadius = diameter / 2 + margin / 2;
                int lx = (int) (cx + Math.cos(mid) * labelRadius);
                int ly = (int) (cy - Math.sin(mid) * labelRadius);
                FontMetrics fm = g2.getFontMetrics();
                String name = d.getNome();
                int textWidth = fm.stringWidth(name);
                int textHeight = fm.getAscent();
                g2.setColor(Color.BLACK);
                g2.drawString(name, lx - textWidth / 2, ly + textHeight / 2);

                startAngle += arc;
            }

            g2.setColor(Color.BLACK);
            g2.drawOval(x, y, diameter, diameter);
        }
    }}
