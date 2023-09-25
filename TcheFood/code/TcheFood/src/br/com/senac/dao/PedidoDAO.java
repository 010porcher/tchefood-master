package br.com.senac.dao;

import br.com.senac.infra.ConexaoMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PedidoDAO {
    // Método para inserir um pedido no banco de dados
    public void inserirPedido(int usuarioId, String formaPagamento, double valorTotal) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtém uma conexão com o banco de dados usando a classe ConexaoMysql
            conexao = ConexaoMysql.obterConexao();

            // SQL para inserir um novo pedido na tabela 'pedidos'
            String sqlInserirPedido = "INSERT INTO pedidos (usuario_id, forma_pagamento, valor_total) VALUES (?, ?, ?)";

            preparedStatement = conexao.prepareStatement(sqlInserirPedido);
            preparedStatement.setInt(1, usuarioId);
            preparedStatement.setString(2, formaPagamento);
            preparedStatement.setDouble(3, valorTotal);

            // Executa a consulta SQL
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            // Lide com exceções adequadamente
            e.printStackTrace();
        } finally {
            // Feche a conexão e o PreparedStatement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Outros métodos da classe PedidoDAO
}
