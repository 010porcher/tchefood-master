package br.com.senac.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPagamento {
    public JPanel JPPagamento;
    private JLabel JLPagamento;
    private JRadioButton PIXRadioButton;
    private JRadioButton cartãoDeCréditoRadioButton;
    private JTextField textFieldChavePix;
    private JTextField textFieldNumeroCartao;
    private JTextField textFieldValorAPagar;
    private JButton confirmarPagamentoButton;
    private JLabel JLEscolhaPagamento;
    private JLabel JLChavePix;
    private JLabel JLNumeroCartao;
    private JLabel JLValor;
    private ButtonGroup radioGroup; // Grupo de RadioB

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaPagamento");
        frame.setContentPane(new TelaPagamento().JPPagamento);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TelaPagamento() {

        radioGroup = new ButtonGroup(); // Cria um grupo para os RadioButtons

        // Adiciona os RadioButtons ao grupo
        radioGroup.add(PIXRadioButton);
        radioGroup.add(cartãoDeCréditoRadioButton);

        textFieldChavePix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        textFieldNumeroCartao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        textFieldValorAPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        confirmarPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        PIXRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldChavePix.setEnabled(true);
                textFieldNumeroCartao.setEnabled(false);
            }
        });
        cartãoDeCréditoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica se o cartãoDeCréditoRadioButton está selecionado
                textFieldChavePix.setEnabled(false);
                textFieldNumeroCartao.setEnabled(true);
            }
        });
    }

    public void atualizarValor(double precoProduto) {
        double valorAtual = Double.parseDouble(textFieldValorAPagar.getText());
        valorAtual += precoProduto;
        textFieldValorAPagar.setText(String.valueOf(valorAtual));
    }
}
