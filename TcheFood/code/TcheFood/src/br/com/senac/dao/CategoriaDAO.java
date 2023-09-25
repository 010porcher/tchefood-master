package br.com.senac.dao;

import br.com.senac.infra.ConexaoMysql;
import br.com.senac.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // Método para obter todas as categorias do banco de dados
    public List<Categoria> obterTodasCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String consultaSQL = "SELECT * FROM categorias";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                Categoria categoria = new Categoria(id, nome);
                categorias.add(categoria);
            }

            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public boolean inserirCategoria(Categoria categoria) {
        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String insercaoSQL = "INSERT INTO categorias (nome) VALUES (?)";
            PreparedStatement statement = conexao.prepareStatement(insercaoSQL);
            statement.setString(1, categoria.getNome());

            int linhasAfetadas = statement.executeUpdate();
            conexao.close();

            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi inserida
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    public boolean atualizarCategoria(Categoria categoria) {
        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String atualizacaoSQL = "UPDATE categorias SET nome = ? WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(atualizacaoSQL);
            statement.setString(1, categoria.getNome());
            statement.setInt(2, categoria.getId());

            int linhasAfetadas = statement.executeUpdate();
            conexao.close();

            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi atualizada
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    public boolean excluirCategoria(int idCategoria) {
        try {
            Connection conexao = ConexaoMysql.obterConexao();
            String exclusaoSQL = "DELETE FROM categorias WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(exclusaoSQL);
            statement.setInt(1, idCategoria);

            int linhasAfetadas = statement.executeUpdate();
            conexao.close();

            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi excluída
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }
}
