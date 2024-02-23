import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;


public class GeradorSenhasGUI extends JFrame {
    private JComboBox<Integer> comprimentoComboBox, especiaisComboBox, maiusculasComboBox, numerosComboBox;
    private JTextArea senhaTextArea;

    public GeradorSenhasGUI() {
        setTitle("Gerador de Senhas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));

        panel.add(new JLabel("Comprimento da Senha:"));
        comprimentoComboBox = new JComboBox<>(criarArrayDeNumeros(1, 20));
        panel.add(comprimentoComboBox);

        panel.add(new JLabel("Caracteres Especiais:"));
        especiaisComboBox = new JComboBox<>(criarArrayDeNumeros(0, 20));
        panel.add(especiaisComboBox);

        panel.add(new JLabel("Letras Maiúsculas:"));
        maiusculasComboBox = new JComboBox<>(criarArrayDeNumeros(0, 20));
        panel.add(maiusculasComboBox);

        panel.add(new JLabel("Quantidade de Números:"));
        numerosComboBox = new JComboBox<>(criarArrayDeNumeros(0, 9));
        panel.add(numerosComboBox);

        JButton gerarButton = new JButton("Gerar Senha");
        gerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSenha();
            }
        });
        panel.add(gerarButton);

        senhaTextArea = new JTextArea();
        senhaTextArea.setEditable(false);
        panel.add(new JScrollPane(senhaTextArea));

        add(panel);
    }

    private Integer[] criarArrayDeNumeros(int inicio, int fim) {
        Integer[] numeros = new Integer[fim - inicio + 1];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = Integer.valueOf(inicio + i);
        }
        return numeros;
    }

    private void gerarSenha() {
        try {
            int comprimento = Integer.parseInt(comprimentoComboBox.getSelectedItem().toString());
            int especiais = Integer.parseInt(especiaisComboBox.getSelectedItem().toString());
            int maiusculas = Integer.parseInt(maiusculasComboBox.getSelectedItem().toString());
            int numeros = Integer.parseInt(numerosComboBox.getSelectedItem().toString());

            String senhaGerada = GeradorSenhas.gerarSenha(comprimento, especiais, maiusculas, numeros);
            senhaTextArea.setText(senhaGerada);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GeradorSenhasGUI().setVisible(true);
            }
        });
    }

}
