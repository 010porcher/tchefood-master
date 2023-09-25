package br.com.senac.dao;

import br.com.senac.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.senac.infra.ConexaoMysql.obterConexao;

public class ProdutoDAO {

    // Método para obter todos os produtos do banco de dados
    public List<Produto> obterTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try {
            Connection conexao = obterConexao();
            String consultaSQL = "SELECT id, nome, descricao, preco FROM produtos";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                double preco = resultSet.getDouble("preco");

                Produto produto = new Produto(id, nome, descricao, preco);
                produtos.add(produto);
            }

            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public double obterPrecoItem(String nomeItem) {
        double preco = 0.0;
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Obtenha a conexão com o banco de dados (substitua com sua implementação)
            conexao = obterConexao();

            // Consulta SQL para buscar o preço do item pelo nome
            String consultaSQL = "SELECT preco FROM produtos WHERE nome = ?";
            statement = conexao.prepareStatement(consultaSQL);
            statement.setString(1, nomeItem); // Define o parâmetro com o nome do item

            resultSet = statement.executeQuery();

            // Verifica se o item foi encontrado e obtém o preço
            if (resultSet.next()) {
                preco = resultSet.getDouble("preco");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Trate exceções aqui de acordo com as necessidades do seu aplicativo
        } finally {
            try {
                // Feche todas as conexões e recursos
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return preco;
    }

    public String obterNomesDosProdutosDoBancoDeDados(int idProduto) {
        String nomeProduto = null;

        try {
            Connection conexao = obterConexao();
            String consultaSQL = "SELECT nome FROM produtos WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(consultaSQL);
            statement.setInt(1, idProduto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomeProduto = resultSet.getString("nome");
            }

            conexao.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nomeProduto;
    }

    public List<Integer> obterIDsDosProdutosParaExibir() {
        List<Integer> idsProdutos = new ArrayList<>();

        idsProdutos.add(1);
        idsProdutos.add(2);
        idsProdutos.add(3);
        idsProdutos.add(4);
        idsProdutos.add(5);
        idsProdutos.add(6);
        idsProdutos.add(7);
        idsProdutos.add(8);

        return idsProdutos;
    }

    public boolean cadastrarNovoProduto(Produto novoProduto) {
        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = obterConexao();

            // Consulta SQL para inserir um novo produto
            String inserirSQL = "INSERT INTO produtos (nome, descricao, preco) VALUES (?, ?, ?)";
            statement = conexao.prepareStatement(inserirSQL);
            statement.setString(1, novoProduto.getNome());
            statement.setString(2, novoProduto.getDescricao());
            statement.setDouble(3, novoProduto.getPreco());

            int linhasAfetadas = statement.executeUpdate();

            // Verifica se o produto foi cadastrado com sucesso
            if (linhasAfetadas > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Trate exceções aqui de acordo com as necessidades do seu aplicativo
        } finally {
            try {
                // Feche todas as conexões e recursos
                if (statement != null) statement.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // O cadastro falhou
    }

    public int adicionarProduto(String nome, String descricao, double preco, int categoriaId) {
        Connection connection = null;
        PreparedStatement statement = null;
        int novoID = -1; // Valor padrão para indicar falha na inserção

        try {
            // Obtenha uma conexão com o banco de dados (substitua com sua lógica de conexão)
            connection = obterConexao();

            // SQL para inserir um novo produto (substitua com a estrutura da sua tabela)
            String sql = "INSERT INTO produtos (nome, descricao, preco, categoria_id) VALUES (?, ?, ?, ?)";

            // Crie a instrução preparada
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Preencha os parâmetros com os valores do produto
            statement.setString(1, nome);
            statement.setString(2, descricao);
            statement.setDouble(3, preco);
            statement.setString(4, String.valueOf(categoriaId));

            // Execute a instrução e obtenha o ID gerado pelo banco de dados
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                // A inserção foi bem-sucedida, obtenha o ID gerado
                novoID = obterIDGerado(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Lide com exceções de SQL aqui
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

        return novoID;
    }

    // ...

    private int obterIDGerado(PreparedStatement statement) throws SQLException {
        // Obtém o ID gerado pelo banco de dados após uma inserção bem-sucedida
        int novoID = -1;
        java.sql.ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            novoID = generatedKeys.getInt(1);
        }
        return novoID;
    }
}