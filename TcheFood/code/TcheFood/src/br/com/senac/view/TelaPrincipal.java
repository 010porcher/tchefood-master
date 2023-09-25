package br.com.senac.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal {

    public JPanel JPTela;
    private JButton cadastroButton;
    private JButton loginButton;
    private JButton cardápioButton;
    private JSeparator JLSeparador;
    private JSeparator JLSeparador2;
    private JLabel JLTitulo;
    private JButton seusPedidosButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaPrincipal");
        frame.setContentPane(new TelaPrincipal().JPTela);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        instance = new TelaPrincipal();
    }

    private static TelaPrincipal instance;

    public static TelaPrincipal getInstance() {
        return instance;
    }

    public TelaPrincipal() {

        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroUser telaCadastroUser = new TelaCadastroUser();

                JFrame frameTelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(JPTela);
                frameTelaPrincipal.setVisible(false);

                JFrame frameTelaCadastroUser = new JFrame("TelaCadastroUser");
                frameTelaCadastroUser.setContentPane(telaCadastroUser.JPTelaCadastro);
                frameTelaCadastroUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameTelaCadastroUser.pack();
                frameTelaCadastroUser.setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin telaLogin = new TelaLogin();

                JFrame frameTelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(JPTela);
                frameTelaPrincipal.setVisible(false);

                JFrame frameTelaLogin = new JFrame("TelaLogin");
                frameTelaLogin.setContentPane(telaLogin.JPTelaLogin);
                frameTelaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameTelaLogin.pack();
                frameTelaLogin.setVisible(true);
            }
        });

        cardápioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCardápio telaCardapio = new TelaCardápio();

                JFrame frameTelaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(JPTela);
                frameTelaPrincipal.setVisible(false);

                JFrame frameTelaCardapio = new JFrame("TelaCardapio");
                frameTelaCardapio.setContentPane(telaCardapio.JPCardapio());
                frameTelaCardapio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameTelaCardapio.pack();
                frameTelaCardapio.setVisible(true);
            }
        });
    }
}

