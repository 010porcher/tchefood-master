package br.com.senac.view;

import br.com.senac.infra.ConexaoMysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TelaLogin {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    public JPanel JPTelaLogin;
    private JLabel JLLogin;
    private JLabel JLUser;
    private JLabel JLSenha;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaLogin");
        frame.setContentPane(new TelaLogin().JPTelaLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TelaLogin() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText(); // Obtenha o nome de usuário
                String senhas = new String(passwordField1.getPassword()); // Obtenha a senha

                // Verifique o usuário e a senha no banco de dados (substitua pela sua lógica)
                if (verificarCredenciais(usuario, senhas)) {
                    JOptionPane.showMessageDialog(JPTelaLogin, "Login bem-sucedido!");

                    // Redirecione para a tela 'TelaPrincipal'
                    JFrame frameTelaLogin = (JFrame) SwingUtilities.getWindowAncestor(JPTelaLogin);
                    frameTelaLogin.setVisible(false);

                    JFrame frameTelaPrincipal = new JFrame("TelaPrincipal");
                    frameTelaPrincipal.setContentPane(new TelaPrincipal().JPTela);
                    frameTelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameTelaPrincipal.pack();
                    frameTelaPrincipal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(JPTelaLogin, "Usuário ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean verificarCredenciais(String usuario, String senha) {
        // Lógica para verificar as credenciais no banco de dados
        // Substitua pelo código de verificação do banco de dados

        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String consultaSQL = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            statement.setString(1, usuario);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            boolean credenciaisCorretas = resultSet.next(); // Verifica se encontrou algum registro

            conexao.close();
            return credenciaisCorretas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
