package br.com.senac.dao;

import br.com.senac.infra.ConexaoMysql;
import br.com.senac.model.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    // Método para inserir um novo pagamento no banco de dados
    public void inserirPagamento(Pagamento pagamento) {
        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String consultaSQL = "INSERT INTO pagamentos (pedido_id, valor, data_pagamento, status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            statement.setInt(1, pagamento.getPedidoId());
            statement.setBigDecimal(2, pagamento.getValor());
            statement.setDate(3, new java.sql.Date(pagamento.getDataPagamento().getTime()));
            statement.setString(4, pagamento.getStatus());
            statement.executeUpdate();
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void atualizarPagamento(Pagamento pagamento) {
        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String consultaSQL = "UPDATE pagamentos SET pedido_id = ?, valor = ?, data_pagamento = ?, status = ? WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            statement.setInt(1, pagamento.getPedidoId());
            statement.setBigDecimal(2, pagamento.getValor());
            statement.setDate(3, new java.sql.Date(pagamento.getDataPagamento().getTime()));
            statement.setString(4, pagamento.getStatus());
            statement.setInt(5, pagamento.getId());
            statement.executeUpdate();
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar todos os pagamentos no banco de dados
    public List<Pagamento> buscarTodosPagamentos() {
        List<Pagamento> pagamentos = new ArrayList<>();

        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String consultaSQL = "SELECT * FROM pagamentos";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(resultSet.getInt("id"));
                pagamento.setPedidoId(resultSet.getInt("pedido_id"));
                pagamento.setValor(resultSet.getBigDecimal("valor"));
                pagamento.setDataPagamento(resultSet.getDate("data_pagamento"));
                pagamento.setStatus(resultSet.getString("status"));
                pagamentos.add(pagamento);
            }
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }

    // Método para excluir um pagamento pelo ID
    public void excluirPagamento(int id) {
        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String consultaSQL = "DELETE FROM pagamentos WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            statement.setInt(1, id);
            statement.executeUpdate();
            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
