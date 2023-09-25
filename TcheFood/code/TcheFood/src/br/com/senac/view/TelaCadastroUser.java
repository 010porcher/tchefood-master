package br.com.senac.view;

import br.com.senac.infra.ConexaoMysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastroUser {
    public JPanel JPTelaCadastro;
    private JLabel JLTituloTchefood;
    private JTextField textField1;
    private JTextField textField3;
    private JButton cadastrarButton;
    private JButton voltarButton;
    private JLabel JLNome;
    private JLabel JLSenha;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaCadastroUser");
        frame.setContentPane(new TelaCadastroUser().JPTelaCadastro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TelaCadastroUser() {

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText(); // Obtenha o nome de usuário
                String senha = textField3.getText(); // Obtenha a senha

                // Insira o novo usuário no banco de dados (substitua pela sua lógica)
                if (cadastrarUsuario(usuario, senha)) {
                    // Sucesso no cadastro, exiba uma mensagem ou redirecione para a próxima tela
                    JOptionPane.showMessageDialog(JPTelaCadastro, "Cadastro realizado com sucesso!");
                } else {
                    // Falha no cadastro, exiba uma mensagem de erro
                    JOptionPane.showMessageDialog(JPTelaCadastro, "Erro ao cadastrar usuário", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();

                // Fechar a janela atual (TelaCadastroUser)
                JFrame frameTelaCadastroUser = (JFrame) SwingUtilities.getWindowAncestor(JPTelaCadastro);
                frameTelaCadastroUser.dispose();

                // Exibir a TelaPrincipal
                JFrame frameTelaPrincipal = new JFrame("TelaPrincipal");
                frameTelaPrincipal.setContentPane(telaPrincipal.JPTela);
                frameTelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameTelaPrincipal.pack();
                frameTelaPrincipal.setVisible(true);
            }
        });
    }

    private boolean cadastrarUsuario(String usuario, String senha) {
        // Lógica para cadastrar o usuário no banco de dados
        // Substitua pelo código de inserção no banco de dados

        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String insercaoSQL = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(insercaoSQL);
            statement.setString(1, usuario);
            statement.setString(2, senha);
            int linhasAfetadas = statement.executeUpdate();

            conexao.close();
            return linhasAfetadas > 0; // Retorna verdadeiro se houve inserção de pelo menos uma linha
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Trate exceções aqui de acordo com as necessidades do seu aplicativo
            return false; // Em caso de erro, considere como falha no cadastro
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
